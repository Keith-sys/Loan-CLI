package org.keith.core;
import java.util.Scanner;

public class LoanInput{
    private Integer principle ,
                    terms,
                    paymentAmount,
                    grossIncome;
    private Double interest;
    SingletonScanner singletonScanner = SingletonScanner.getInstance();
    Scanner scanner = singletonScanner.getScanner();

    // Check Terms based on the type of loan
    private void checkTerms(int maxTerms){
        if (mustBeLessThanMaxTerms(maxTerms)) {
            getPaymentAmount();
        } else {
            System.out.println("Maximum allowed term is " + maxTerms + " years");
            getTermsAmount();
        }
    }

    private Boolean mustBeLessThanMaxTerms(int maxTerms){
        return terms <= maxTerms;
    }

    public void getLoanInfo(int maxTerms){
        getPrincipleAmount();
        getTermsAmount();
        checkTerms(maxTerms);
        getInterest();
        getGrossIncome();
        Amortization amortization = new Amortization(principle,
                                                     terms,
                                                     paymentAmount,
                                                     interest,
                                                     grossIncome);
        amortization.printEvaluation();
    }

    private void getPrincipleAmount(){
        System.out.print("""
                Enter your loan information.
                Loan amount:\s""");
        principle = scanner.nextInt();
    }

    private void getTermsAmount(){
        System.out.print("Terms(year): ");
        terms = scanner.nextInt();
    }

    private void getPaymentAmount(){
        System.out.print("Number of payments in a single year: ");
        paymentAmount = scanner.nextInt();
    }

    private void getInterest(){
        System.out.print("Interest rate: ");
        interest = scanner.nextDouble();
    }

    private void getGrossIncome(){
        System.out.print("Gross income: ");
        grossIncome = scanner.nextInt();
        scanner.nextLine();
    }
}
