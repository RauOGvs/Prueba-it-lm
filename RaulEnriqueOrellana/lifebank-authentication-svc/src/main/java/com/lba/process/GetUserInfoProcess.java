package com.lba.process;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lba.entity.UsrUser;
import com.lba.entity.UsrUserSession;
import com.lba.jwt.repository.IJwt;
import com.lba.repository.IGetSessions;
import com.lba.repository.implementation.GetInfoImplementation;
import com.lba.repository.implementation.GetInfoSessionImplementation;
import com.lba.service.getinfo.request.UserInfoRequest;
import com.lba.service.getinfo.response.ResponseJwt;

@Service
public class GetUserInfoProcess {

	private IJwt getJwt;
	private Logger log;
	private GetInfoImplementation getInfo;
	private GetInfoSessionImplementation getInfoSession;
	private IGetSessions getSession;

	public GetUserInfoProcess(IJwt getJwt, GetInfoImplementation getInfo,
			GetInfoSessionImplementation getInfoSession, IGetSessions getSession) {
		this.getJwt = getJwt;
		this.getInfo = getInfo;
		this.log = LoggerFactory.getLogger(getClass());
		this.getInfoSession = getInfoSession;
		this.getSession = getSession;
	}

	public ResponseEntity<?> getInfo(UserInfoRequest request) {

		
		try {
			UsrUser user = getInfo.getInfoUser(request.getUserId());
			
			if (user != null && user.getUsrStatus()==1) {
				ResponseJwt response = new ResponseJwt();
				Long totalIntentos = getSession.findByUsrUserAndUusSesssionDateAndUusSessionStatus(user.getUsrUserName(), 0);
				String hash = getHashSHA512(request.getPassword(), request.getUserId());
				log.info("pass: {}", hash);
				if (hash.equals(user.getUsrPassword()) && request.getUserId().equals(user.getUsrUserName())) {
					String jwt = getJwt.generateJwt(request.getUserId(), request.getIp());
					log.info("JWT: {}", jwt);
					response.setJwt(jwt);
//				getJwt.validateJwt(jwt);
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					
					saveSessionFail(request.getUserId(), user, totalIntentos);
				}
			}

		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	private void saveSessionFail(String userName, UsrUser user, Long totalIntentos) {
		/*
		 * 0 = FAIL 1 = SUCCESS
		 */
		UsrUserSession session = new UsrUserSession();
		session.setUsrUser(user);
		session.setUusSessionDate(new Timestamp(new Date().getTime()));
		session.setUusSessionStatus(0);
		boolean status= false; 
		totalIntentos = totalIntentos+1;
		if (userName.equals(user.getUsrUserName()) && totalIntentos<5) {
			status = getInfoSession.save(session);
			if (status) {
				log.info("insert success");
			}
		}else {
			
			 user.setUsrStatus(0);
			 status = getInfo.save(user);
			if (status) {
				log.info("user has been blocked");
			}
			
		}
		
	}

	private String getHashSHA512(String password, String userID) {
		String generatedPassword = null;
		
		try {
			byte[] encodeUserID = Base64.encodeBase64(userID.getBytes());
			String encodeUserId = new String(encodeUserID);
//			log.info("USERID:{} --> Encriptado: {}", userID, encodeUserId);
			byte[] encodePass = Base64.encodeBase64(password.getBytes());
			String encodePassword = new String(encodePass);
//			log.info("PASS:{} --> Encriptado: {}", password, encodePassword);
			StringBuilder sb = new StringBuilder();
			sb.append(encodeUserId)
			.append(encodePassword);
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = md.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
			generatedPassword = Hex.encodeHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
		}
		return generatedPassword;
	}
}
