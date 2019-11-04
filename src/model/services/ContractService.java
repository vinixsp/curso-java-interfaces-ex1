package model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	public static void processContract(Contract c, int numberOfInstallments, PaymentService p) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getDateSigned());
		
		Double baseValue = c.getAmount() / numberOfInstallments;
		
		for (int i=1; i<=numberOfInstallments; i++) {
			cal.add(Calendar.MONTH, 1);
			Double finalValue = baseValue + p.interest(baseValue, i) + p.paymentFee(baseValue);
			c.addInstallment(new Installment(cal.getTime(), finalValue));
		}
	}

	public static List<Installment> simulateContract(Contract c, int numberOfInstallments, PaymentService p) {
		List<Installment> installments = new ArrayList<>();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getDateSigned());
		
		Double baseValue = c.getAmount() / numberOfInstallments;
		
		for (int i=1; i<=numberOfInstallments; i++) {
			cal.add(Calendar.MONTH, 1);
			Double finalValue = baseValue + p.interest(baseValue, i) + p.paymentFee(baseValue);
			installments.add(new Installment(cal.getTime(), finalValue));
		}
		
		return installments;
	}
}
