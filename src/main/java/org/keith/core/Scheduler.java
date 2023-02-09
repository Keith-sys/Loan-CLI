package org.keith.core;

import java.text.NumberFormat;

public class Scheduler{
    private final int startPeriod = 1,
                      hundred = 100;
    private final Integer totalPeriod;
    private Integer principle;
    private Integer endPeriod,
                    numberPayment;
    private Double monthlyPayments,
                   balance,
                   annualInterest,
                   periodInterest,
                   principlePaid = 0.0,
                   interestPaid = 0.0;

    protected Scheduler(Integer totalPeriod,
                        Double monthlyPayments,
                        Integer principle,
                        Double annualInterest,
                        Integer numberPayment){
        this.totalPeriod = totalPeriod;
        this.monthlyPayments = monthlyPayments;
        this.principle = principle;
        this.annualInterest = annualInterest;
        endPeriod = totalPeriod;
        this.numberPayment = numberPayment;
    }

    protected void getTable(){
        balance = convertToDouble(principle);
        periodInterest = calculatePeriodInterest(annualInterest, numberPayment);
        for(int period = startPeriod; period <= endPeriod; period++){
//            interestPaid = balance * periodInterest;
            interestPaid = calculateInterestPaid();
//            principlePaid = monthlyPayments - interestPaid;
            principlePaid = calculatePrinciplePaid();
//            balance -= principlePaid;
            balance = calculateNewBalance();
            System.out.println("Period: " + period + ", Principle: " + formatToCurrency(principlePaid) + ", Interest: " + formatToCurrency(interestPaid) + ", Balance: " + formatToCurrency(balance));
        }
        System.out.println("Monthly Payment: " + formatToCurrency(monthlyPayments));
        System.out.println("Total Payment is " + formatToCurrency(calculateTotalPaymentAmount()));
        System.out.println("Total Interest paid: " + formatToCurrency(calculateTotalInterestPaid()));
    }

    private Double calculateTotalInterestPaid(){
        return calculateTotalPaymentAmount() - principle;
    }

    private Double calculatePeriodInterest(Double interest,Integer amountPayment){
        return interest / hundred / amountPayment;
    }

    private Double calculateTotalPaymentAmount(){
        return monthlyPayments * totalPeriod;
    }

    private Double convertToDouble(Integer value){
        return Double.valueOf(value);
    }

    private String formatToCurrency(Double value){
        NumberFormat cf = NumberFormat.getCurrencyInstance();
        return cf.format(value);
    }

    private Double calculateInterestPaid(){
        return balance * periodInterest;
    }

    private Double calculatePrinciplePaid(){
        return monthlyPayments - interestPaid;
    }
    private Double calculateNewBalance(){
        return balance -= principlePaid;
    }
}
