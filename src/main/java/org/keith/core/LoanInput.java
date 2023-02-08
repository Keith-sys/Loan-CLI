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
    private void checkTerms(Integer maxTerms){
        while(true) {
            if (mustBeLessThanMaxTerms(maxTerms)) {
                getPaymentAmount();
            } else {
                System.out.println("Maximum allowed term is " + maxTerms + " years");
                getTermsAmount();
            }
            break;
        }
    }

    private Boolean mustBeLessThanMaxTerms(Integer maxTerms){
        return terms <= maxTerms;
    }

    public void getLoanInfo(Integer maxTerms){
        principle = getPrincipleAmount();
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

    private Integer getPrincipleAmount(){
        System.out.print("""
                Enter your loan information.
                Loan amount:\s""");
        return Integer.parseInt(scanner.nextLine());
    }

    private void getTermsAmount(){
        System.out.print("Terms(year): ");
        terms = Integer.parseInt(scanner.nextLine());
    }

    private void getPaymentAmount(){
        System.out.print("Number of payments in a single year: ");
        paymentAmount = Integer.parseInt(scanner.nextLine());
    }

    private void getInterest(){
        System.out.print("Interest rate: ");
        interest = Double.parseDouble(scanner.nextLine());
    }

    private void getGrossIncome(){
        System.out.print("Gross income: ");
        grossIncome = Integer.parseInt(scanner.nextLine());
//        scanner.nextLine();
    }
}
