package com.lbint.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbint.repository.IRestAPI;
import com.lbint.repository.IValidation;
import com.lbint.service.pojo.response.AccountClientPojo;
import com.lbint.utility.UtilResponse;

@Service
public class GetAccountProcess {

	private Logger log;
	private IValidation validations;
	private IRestAPI  restAPI;
	private UtilResponse responseUtil;

	@Autowired
	public GetAccountProcess(IValidation validations, IRestAPI  restAPI) {
		this.validations = validations;
		this.restAPI = restAPI;
		this.log = LoggerFactory.getLogger(getClass());
	}

	public ResponseEntity<?> getAccountData(String header, String ip, String clientId) {

		log.info("RequestHeader: {}", header);
		boolean isEmpty = validations.validateHeaderEmpty(header);
		if (isEmpty) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		Integer isValid = validations.isValidaToken(header, ip);
		if(isValid==200) {
			AccountClientPojo response = restAPI.getAccountsClient(clientId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			return UtilResponse.getResponse(isValid);
		}
	}

}
