package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contract {
	
	private Integer number;
	private Date dateSigned;
	private Double amount;
	
	private List<Installment> installments = new ArrayList<>();
	
	public Contract() {
	}

	public Contract(Integer number, Date dateSigned, Double amount) {
		this.number = number;
		this.dateSigned = dateSigned;
		this.amount = amount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDateSigned() {
		return dateSigned;
	}

	public void setDateSigned(Date dateSigned) {
		this.dateSigned = dateSigned;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public void addInstallment(Installment i) {
		installments.add(i);
	}
	
	public void removeInstallment(Installment i) {
		installments.remove(i);
	}

	public List<Installment> getInstallments() {
		return installments;
	}
}
