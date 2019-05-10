package com.lba.process;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lba.entity.UsrUser;
import com.lba.jwt.repository.IJwt;
import com.lba.repository.GetInfoRepositoryImplementation;
import com.lba.service.getinfo.request.UserInfoRequest;
import com.lba.service.getinfo.response.ResponseJwt;

@Service
public class GetUserInfoProcess {

	private IJwt getJwt;
	private Logger log;
	private GetInfoRepositoryImplementation getInfo;

	public GetUserInfoProcess(IJwt getJwt, GetInfoRepositoryImplementation getInfo) {
		this.getJwt = getJwt;
		this.getInfo = getInfo;
		this.log = LoggerFactory.getLogger(getClass());
	}

	public ResponseEntity<?> getInfo(UserInfoRequest request) {

		try {
			UsrUser user = getInfo.getInfoUser(request.getUserId());
			ResponseJwt response = new ResponseJwt();
			String hash = getHashSHA512(request.getPassword(), request.getUserId());
			log.info("pass: {}", hash);
			if (hash.equals(user.getUsrPassword())) {
				String jwt = getJwt.generateJwt(request.getUserId(), request.getIp());
				log.info("JWT: {}", jwt);
				response.setJwt(jwt);
//				getJwt.validateJwt(jwt);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	private String getHashSHA512(String StringToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest(StringToHash.getBytes(StandardCharsets.UTF_8));
			generatedPassword = Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return generatedPassword;
	}
}
