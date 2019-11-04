package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.BraspagPaymentService;
import model.services.ContractService;
import model.services.PaymentService;
import model.services.PaypalPaymentService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (DD/MM/YYYY): ");
		Date dateSigned = sdf.parse(sc.nextLine());
		System.out.print("Contract value: ");
		Double amount = sc.nextDouble();
		System.out.print("Enter the number of installments: ");
		int numberOfInstallments = sc.nextInt();

		Contract c = new Contract(number, dateSigned, amount);
		
		List<PaymentService> ps = new ArrayList<>();
		ps.add(new PaypalPaymentService());
		ps.add(new BraspagPaymentService());
		
		System.out.println();
		System.out.println("Simulação:");

		for (PaymentService service : ps) {
			System.out.println(service.getName());
			System.out.println("Installments:");
			for (Installment i : ContractService.simulateContract(c, numberOfInstallments, service)) {
				System.out.println(sdf.format(i.getDueDate()) + " - " + String.format("%.2f", i.getAmount()));
			}
			System.out.println();
		}

		System.out.println("Informe a opção escolhida:");
		int cont = 0;
		for (PaymentService service : ps) {
			System.out.println("[" + cont + "] "+ service.getName());
			cont += 1;
		}
		int opcao = sc.nextInt();
		
		System.out.println();
		System.out.println("Opção escolhida: " + ps.get(opcao).getName());
		ContractService.processContract(c, numberOfInstallments, ps.get(opcao));
		System.out.println("Installments:");
		for (Installment i : c.getInstallments()) {
			System.out.println(sdf.format(i.getDueDate()) + " - " + String.format("%.2f", i.getAmount()));
		}
		
		sc.close();
	}

}
