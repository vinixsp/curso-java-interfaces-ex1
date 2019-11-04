package model.services;

public class BraspagPaymentService implements PaymentService {
	
	private static final String NAME = "Braspag";
	private static final Double INTEREST_RATE_PERCENTAGE = 0.0125;
	private static final Double FEE_PERCENTAGE = 0.02;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public double interest(double amount, int months) {
		return amount * INTEREST_RATE_PERCENTAGE * months;
	}

	@Override
	public double paymentFee(double amount) {
		return amount * FEE_PERCENTAGE;
	}
}
