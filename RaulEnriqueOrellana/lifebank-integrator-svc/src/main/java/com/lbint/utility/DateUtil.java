package com.lbint.utility;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class DateUtil {
	@Autowired
	static Logger log = LoggerFactory.getLogger(DateUtil.class);
	public static final String STANDAR_FORMAT_DATE="yyyy-MM-dd";
	public static final String STANDAR_FORMAT_DATE_TIME="yyyy-MM-dd HH:mm:ss";
	public static final String STANDAR_FORMAT_TIME="HH:mm:ss";
	public static final String STANDAR_FORMAT_HOUR_MINUTE="HH:mm";
	private static final String ERRORMESSAGE="Microservicio find-portal-svc error: {} en linea: {} en metodo: {} ";
	
	public static String getDateAsStringFromDate(Date date,String formatRequired){
		DateFormat df = new SimpleDateFormat(formatRequired,Locale.ENGLISH);
		if(date!=null)
			return df.format(date);
		else
			return "";
	}
	public static String getTimeAsStringFromString(String hour,String actualFormat, String newFormat){
		DateFormat df = new SimpleDateFormat(actualFormat,Locale.ENGLISH);
		DateFormat newDF=new SimpleDateFormat(newFormat,Locale.ENGLISH);
		Date newDate=null;
		if(hour!=null && !"".equals(hour)){
			try {
				newDate=df.parse(hour);
			} catch (ParseException e) {
				e.printStackTrace();
				
			}
			return newDF.format(newDate);
		}
		else{
			return "";
		}
	}

	public static String getDateDifferentFormat(String date,String actualFormat, String newFormat) {
		
		DateFormat df = new SimpleDateFormat(actualFormat,Locale.ENGLISH);
		DateFormat newDF=new SimpleDateFormat(newFormat,Locale.ENGLISH);
		Date newDate=null;
		if(date!=null && !"".equals(date)){
			try {
				newDate=df.parse(date);
			} catch (ParseException e) {
				log.error(ERRORMESSAGE, e,
						e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
			}
			return newDF.format(newDate);
		}
		else{
			return "";
		}		
	}
	
	public static Date getDateDifferentFormat(Date date,String formatRequired){
		DateFormat df = new SimpleDateFormat(formatRequired,Locale.ENGLISH);
		String dateFormated;
		if(date!=null) {
			try {
				dateFormated=df.format(date);
				return df.parse(dateFormated);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}else
			return null;
	}
	
	public static Date getDateFromString(String date,String actualFormat,String formatRequired){
		DateFormat df = new SimpleDateFormat(actualFormat,Locale.ENGLISH);
		Date newDate;
		if(date!=null) {
			try {
				newDate=df.parse(date);
				return getDateDifferentFormat(newDate, formatRequired);
			} catch (ParseException e) {
				log.error(ERRORMESSAGE, e,
						e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
				return null;
			}
		}else
			return null;
	}
	
	public static List<String> getDateForFlightsCarrousel(List<String> dates, String patronU, String patronD, 
			Locale locale) {
		
		List<String> returnDates = new ArrayList<>();
		SimpleDateFormat sdf;
		
		if("es".equals(locale.toString())) {
			DateFormatSymbols sym = DateFormatSymbols.getInstance(locale);
			sym.setShortMonths(new String[]{ "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"});
			sym.setShortWeekdays(new String[]{ "", "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"});
			sdf = new SimpleDateFormat(patronU, sym);
		} else {
			 sdf = new SimpleDateFormat(patronU, locale);
		}
		
		SimpleDateFormat formatDate = new SimpleDateFormat(patronD);
		Calendar calendar = Calendar.getInstance();
		
		for(int i = 0; i < dates.size(); i++) {
			try {
				calendar.setTime(formatDate.parse(dates.get(i))); 
				calendar.add(Calendar.DATE, 0);
				returnDates.add(sdf.format(calendar.getTime()));
			} catch (Exception e) {
				log.error(ERRORMESSAGE, e,
						e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
				returnDates.add("error");
			}
		}
		
		return returnDates;
	}
	
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException e) {
        	log.error(ERRORMESSAGE, e,
					e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName());
        }
        return xmlCalendar;
    }
}
