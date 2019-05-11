package com.lbint.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilResponse {

	public static ResponseEntity<?> getResponse(Integer isValid) {

		switch (isValid) {

		case 403: {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		case 440: {
			return new ResponseEntity<>(null, HttpStatus.resolve(410));
		}
		default:
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

}
