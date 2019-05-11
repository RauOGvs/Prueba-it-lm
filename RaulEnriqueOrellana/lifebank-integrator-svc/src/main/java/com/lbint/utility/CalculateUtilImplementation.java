package com.lbint.utility;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculateUtilImplementation implements ICalculateUtil {

	@Override
	public double getTotalRealCost(List<Double> listRealCost) {

		double totalRealCost = 0;
		for (double realCost : listRealCost) {

			totalRealCost += realCost;

		}
		return Double.valueOf(CurrencyUtil.roundDecimals(String.valueOf(totalRealCost),2));
	}

	@Override
	public double getTotalCost(List<Double> listCost) {
		double totalCost = 0;
		for (double realCost : listCost) {

			totalCost += realCost;

		}
		return  Double.valueOf(CurrencyUtil.roundDecimals(String.valueOf(totalCost),2));
	}

	@Override
	public String getTotalMiles(List<String> listMiles) {
		int totalMiles = 0;
		Integer milesSum;
		for (String miles : listMiles) {
			
			milesSum = Integer.valueOf(miles);
			
			totalMiles += milesSum;

		}
		return String.valueOf(totalMiles);
	}

}
