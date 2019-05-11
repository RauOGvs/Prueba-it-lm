package com.lbint.utility;

import org.springframework.stereotype.Component;

@Component
public class CurrencyUtil {

	public static String roundDecimals(String value, int decimals) {
		float number = Float.parseFloat(value);
		String formato;

		switch (decimals) {

		case 2:{ 
			formato = String.format("%.2f", number);
			break;
		}
		case 0: {
			formato = String.format("%.0f", number);
			break;
		}
		default: {
			StringBuilder sb = new StringBuilder();
			sb.append("%");
			sb.append(decimals);
			sb.append("f");
			String formatBuilded = sb.toString();
			formato = String.format(formatBuilded, number);
		}

		}
		return formato;

	}

}
