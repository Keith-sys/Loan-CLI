package org.keith.core;

import org.keith.menu.Menu;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Amortization extends LoanDetails {
    private final int ifFavorable = 35,
                      ifMinimumAdequate = 36,
                      ifMaximumAdequate = 40,
                      hundred = 100,
                      one =  1;
    private final String favorable = "Favorable",
                         adequate = "Adequate",
                         highRisk = "High Risk!",
                        yes = "Y",
                        no = "N",
                        scheduleNeedInput = "Do you want a Schedule(Y/N): ",
                        valueFormat = "0.##",
                        ANSI_RESET = "\u001B[0m",
                        ANSI_GREEN = "\u001B[32m",
                        ANSI_RED = "\u001B[31m",
                        ANSI_YELLOW = "\u001B[33m";
    private String input;
    LoanDetails loanDetails = new LoanDetails();

    public Amortization(Integer principle,
                        Integer terms,
                        Integer numberPayment,
                        Double interest,
                        Integer grossIncome){
        loanDetails.setPrinciple(principle);
        loanDetails.setTerms(terms);
        loanDetails.setNumberPayment(numberPayment);
        loanDetails.setAnnualInterestRate(interest);
        loanDetails.setGrossIncome(grossIncome);
    }

    public String evaluateDTI(){
        if(calculateDebtToIncomeRatio() < ifFavorable){
            return ANSI_GREEN + favorable + ANSI_RESET;
        } else if(calculateDebtToIncomeRatio() >= ifMinimumAdequate & calculateDebtToIncomeRatio() <= ifMaximumAdequate){
            return ANSI_YELLOW + adequate + ANSI_RESET;
        } else{
            return ANSI_RED + highRisk + ANSI_RESET;
        }
    }

    private void checkIfSchedulerNeeded(){
        Scheduler scheduler = new Scheduler(calculateTotalPeriods(),
                                            calculateMonthlyPayment(),
                                            loanDetails.getPrinciple(),
                                            loanDetails.getAnnualInterestRate(),
                                            loanDetails.getNumberPayment());
        Menu menu = new Menu();
        input = getInput();

        switch (input.toUpperCase()){
            case yes -> scheduler.getTable();
            case no -> menu.getMenuInput();
            default -> getInput();
        }

    }

    protected void printEvaluation() {
        System.out.println("Monthly payment rate: " + formatToCurrency(calculateMonthlyPayment()));
        System.out.println("Debt to Ratio       : " + formatToTwoDigits(calculateDebtToIncomeRatio()) + "%");
        System.out.println("Your loan is " + evaluateDTI() + " for the bank");
        checkIfSchedulerNeeded();
    }

    public String formatToCurrency(Double value){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(value);
    }

    public String formatToTwoDigits(Double value){
        DecimalFormat df = new DecimalFormat(valueFormat);
        return df.format(value);
    }

    private String getInput(){
        SingletonScanner singletonScanner = SingletonScanner.getInstance();
        return singletonScanner.getString(scheduleNeedInput);
    }

    public Double calculateInterestInDecimals(){
        return loanDetails.getAnnualInterestRate() / hundred;
    }

    public Double annualInterestDividedByPaymentAmount(){
        return calculateInterestInDecimals() / loanDetails.getNumberPayment();
    }

    public Double calculateNumerator(){
        return loanDetails.getPrinciple() * (annualInterestDividedByPaymentAmount());
    }

    public Integer calculateTotalPeriods(){
        return loanDetails.getNumberPayment() * loanDetails.getTerms();
    }

    public Double calculateDenominator(){
        return one - calculateTheSquare((one + annualInterestDividedByPaymentAmount()), -calculateTotalPeriods());
    }

    private Double calculateTheSquare(Double number, Integer square){
        return Math.pow(number, square);
    }

    public Double calculateDebtToIncomeRatio() {
        return calculateMonthlyPayment() / loanDetails.getGrossIncome() * hundred;
    }

    public Double calculateMonthlyPayment(){
        return calculateNumerator() / calculateDenominator();
    }
}