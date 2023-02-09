package org.keith.core;

public class LoanInput{
    private Integer principle ,
                    terms,
                    paymentAmount,
                    grossIncome;
    private Double interest;
    private final String paymentAmountInput =  "Number of payments in a single year: ",
                         principleInput = "Loan amount: ",
                         termsInput =  "Terms(year): ",
                         grossIncomeInput = "Gross income: ",
                         interestInput = "Interest rate: ";
    private boolean isRunningTerms = true;
    SingletonScanner singletonScanner = SingletonScanner.getInstance();

//     Check Terms based on the type of loan
    private void checkTerms(Integer maxTerms){
        while(isRunningTerms) {
            terms = getTermsAmount();

            if(checkIfLessThanMaxTerms(maxTerms)){
                break;
            }
        }
    }

    private Boolean checkIfLessThanMaxTerms(Integer maxTerms){
        return terms <= maxTerms;
    }

    public void getLoanInfo(Integer maxTerms){
        System.out.println("Enter your loan information.");
        principle = getPrincipleAmount();
        checkTerms(maxTerms);

        paymentAmount = getPaymentAmount();
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
        return parseInteger(singletonScanner.getString(principleInput));
    }

    private Integer getTermsAmount(){
        return parseInteger(singletonScanner.getString(termsInput));
    }

    private Integer getPaymentAmount(){
        return parseInteger(singletonScanner.getString(paymentAmountInput));
    }

    private Double getInterest(){
        return parseDouble(singletonScanner.getString(interestInput));
    }

    private Integer getGrossIncome(){
        return parseInteger(singletonScanner.getString(grossIncomeInput));
    }

    private Integer parseInteger(String value){
        return Integer.parseInt(value);
    }

    private Double parseDouble(String value){
        return Double.parseDouble(value);
    }
}