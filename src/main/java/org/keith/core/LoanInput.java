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
                paymentAmount = getPaymentAmount();
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
        terms = getTermsAmount();
        checkTerms(maxTerms);
        interest = getInterest();
        grossIncome = getGrossIncome();
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

    private Integer getTermsAmount(){
        System.out.print("Terms(year): ");
        return Integer.parseInt(scanner.nextLine());
    }

    private Integer getPaymentAmount(){
        System.out.print("Number of payments in a single year: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private Double getInterest(){
        System.out.print("Interest rate: ");
        return Double.parseDouble(scanner.nextLine());
    }

    private Integer getGrossIncome(){

        System.out.print("Gross income: ");
        return Integer.parseInt(scanner.nextLine());
//        scanner.nextLine();
    }
}
