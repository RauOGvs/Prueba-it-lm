package com.lba.repository.implementation;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lba.repository.IGetSessions;

@Service
public class GetTriesImplementation implements IGetSessions {

	private Logger log;

	@PersistenceContext
	private EntityManager em;

	public GetTriesImplementation() {
		this.log = LoggerFactory.getLogger(getClass());
	}

	@Override
	public Long findByUsrUserAndUusSesssionDateAndUusSessionStatus(String userName, int status) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());

			// Desplegamos la fecha
			Date tempDate = cal.getTime();
			System.out.println("Fecha actual: " + tempDate);
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - 3);
			tempDate = cal.getTime();
			System.out.println("Hora modificada: " + tempDate);
			String sql = "SELECT count(1) FROM UsrUserSession uus "
					+ "where uus.usrUser.usrUserName=:name and uus.uusSessionDate between :a and :b"
					+ " and uus.uusSessionStatus=:status";
			Query q = em.createQuery(sql).setParameter("name", userName)
					.setParameter("b", new Timestamp(new Date().getTime()))
					.setParameter("a", new Timestamp(tempDate.getTime())).setParameter("status", status);

			return (Long) q.getSingleResult();
		} catch (Exception e) {
			log.error("Microservicio lifebank-authentication-svc:  error: {} en linea: {} en metodo: {}", e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			return null;
		}
	}
}
