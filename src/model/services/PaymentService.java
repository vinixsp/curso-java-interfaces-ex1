package model.services;

public interface PaymentService {

	String getName();
	double interest(double amount, int months);
	double paymentFee(double amount);
}
