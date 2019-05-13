package com.lbint.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbint.repository.IRestAPI;
import com.lbint.repository.IValidation;
import com.lbint.response.repository.ValueResponse;
import com.lbint.service.pojo.request.TransferAccountPojo;
import com.lbint.service.pojo.request.TransferAccountRequest;
import com.lbint.service.pojo.response.AuthorizationResponse;
import com.lbint.service.pojo.response.ClientAccountResponse;
import com.lbint.utility.UtilResponse;

@Service
public class TransferBankProcess {

	private Logger log;
	private IValidation validations;
	private IRestAPI restAPI;

	public TransferBankProcess(IValidation validations, IRestAPI restAPI) {
		this.log = LoggerFactory.getLogger(getClass());
		this.validations = validations;
		this.restAPI = restAPI;
	}

	public ResponseEntity<?> process(TransferAccountRequest request, String header) {

		try {
			log.info("RequestHeader: {}", header);
			log.info("Request doTransfer: {}", new ObjectMapper().writeValueAsString(request));
			boolean isEmpty = validations.validateHeaderEmpty(header);
			if (isEmpty) {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
			ValueResponse<Integer> isValid = validations.isValidaToken(header, request.getIp());
			if (isValid != null && "200".contentEquals(isValid.getResponseCode())) {
				if (isValid.getValue() == 200) {
					ClientAccountResponse responseOrigin = restAPI.validateAccountBank(request.getAccountOrigin());
					ClientAccountResponse responseDestination = restAPI
							.validateAccountBank(request.getAccountDestination());
					if (responseOrigin != null && responseDestination != null
							&& responseOrigin.getClientID().equals(isValid.getMessage())
							&& responseOrigin.getClientID().equals(isValid.getMessage())) {
						return doTransfer(request, responseOrigin, responseDestination, isValid.getMessage());
					} else {
						return new ResponseEntity<>(responseOrigin, HttpStatus.NOT_FOUND);
					}
				} else {
					return UtilResponse.getResponse(isValid.getValue());
				}
			}
		} catch (Exception e) {
			log.error("Microservicio: lifebank-clients-info-svc: error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return UtilResponse.getResponse(403);
		}
		return UtilResponse.getResponse(403);

	}

	private ResponseEntity<?> doTransfer(TransferAccountRequest request, ClientAccountResponse responseOrigin,
			ClientAccountResponse responseDestination, String clientId) {

		TransferAccountPojo transfer = new TransferAccountPojo();
		try {
			transfer.setAccountIdDestination(responseDestination.getAccountID());
			transfer.setAccountIdOrigin(responseOrigin.getAccountID());
			transfer.setAmount(request.getAccountAmount());
			transfer.setAmountDestination(Double.valueOf(responseDestination.getAmount()));
			transfer.setAmountOrigin(Double.valueOf(responseOrigin.getAmount()));
			transfer.setClientId(clientId);
			transfer.setTypeAccountOrigin(responseOrigin.getTypeAccountInt());
			transfer.setTypeAccountDestinations(responseDestination.getTypeAccountInt());

			if (request.getAccountAmount() > responseOrigin.getAmount()) {
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			}

			String auth = restAPI.doTransfer(transfer);

			if (auth != null && !"500".equals(auth)) {

				return new ResponseEntity<>(new AuthorizationResponse(auth), HttpStatus.OK);

			} else if (auth != null && "500".equals(auth)) {
				return new ResponseEntity<>(auth, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			log.error("Microservicio lifebank-integrator-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	}
}
