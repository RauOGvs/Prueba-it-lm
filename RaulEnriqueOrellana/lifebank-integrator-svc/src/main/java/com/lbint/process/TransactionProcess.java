package com.lbint.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbint.repository.IRestAPI;
import com.lbint.repository.IValidation;
import com.lbint.response.repository.ValueResponse;
import com.lbint.service.pojo.response.AccountTransactionPojo;
import com.lbint.utility.UtilResponse;

@Service
public class TransactionProcess {

	private Logger log;
	private IRestAPI restApi;
	private IValidation validations;

	public TransactionProcess(IRestAPI restApi, IValidation validations) {
		this.restApi = restApi;
		this.validations = validations;
		this.log = LoggerFactory.getLogger(getClass());
	}

	public ResponseEntity<?> process(String header, String accountID, String startDate, String endDate, String ip) {
		String accountOwner = null;
		try {
			log.info("RequestHeader: {}", header);
			boolean isEmpty = validations.validateHeaderEmpty(header);
			if (isEmpty) {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
			ValueResponse<Integer> isValid = validations.isValidaToken(header, ip);

			if (isValid != null && "200".equals(isValid.getResponseCode())) {

				if (isValid.getValue() == 200) {
					accountOwner = restApi.validateAccount(accountID);
					if (validateOwner(accountOwner, isValid.getMessage())) {
						log.info("ACCOUNT IS CORRECT");
						boolean isValidDates = validaFechas(startDate, endDate);
						if (isValidDates) {
							AccountTransactionPojo response = restApi.getTrasactions(accountID, startDate, endDate);
							return new ResponseEntity<>(response, HttpStatus.OK);
						} else {
							return UtilResponse.getResponse(403);
						}
					}else {
						return UtilResponse.getResponse(404);
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
		return UtilResponse.getResponse(404);
	}

	private boolean validateOwner(String accountOwner, String idFromJwt) {
		if (accountOwner == null) {
			return false;
		} else if (accountOwner.equals(idFromJwt)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean validaFechas(String startDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(startDate);
			Date finalDate = format.parse(endDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int initMonth = cal.get(Calendar.MONTH) + 1;
			cal.setTime(finalDate);
			int endMonth = cal.get(Calendar.MONTH) + 1;
			if (date.after(finalDate)) {
				log.info("Start date is after than end Date");
				return false;
			} else {
				int diffMonths = endMonth - initMonth;
				if (diffMonths > 3) {
					log.info("The diff between months is more than 3");
					return false;
				}

			}

			log.info("Dates are valids");
			return true;
		} catch (Exception e) {

		}
		return false;
	}
}
