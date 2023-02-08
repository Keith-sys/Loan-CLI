package org.keith;
import org.junit.jupiter.api.*;
import org.keith.core.Amortization;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmortizationTest {
    Amortization mortgageCalc = new Amortization(500_000, 30, 12, 6.0, 13000);
    @Test
    void interestInDecimalsTest(){
        double expected = 0.06;
        double actual = mortgageCalc.calculateInterestInDecimals();
        assertEquals(expected,actual);
    }

    @Test
    void annualInterestDividedByPaymentAmountTest(){
        double expected = 0.005;
        double actual = mortgageCalc.annualInterestDividedByPaymentAmount();
        assertEquals(expected,actual);
    }

    @Test
    void calculateNumeratorTest(){
        double expected = 2500;
        double actual = mortgageCalc.calculateNumerator();
        assertEquals(expected,actual,0.01);
    }

    @Test
    void totalPeriodsTest(){
        int expected = 360;
        int actual = mortgageCalc.calculateTotalPeriods();
        assertEquals(expected,actual);
    }

    @Test
    void calculateDenominatorTest(){
        double expected = 0.83;
        double actual = mortgageCalc.calculateDenominator();
        assertEquals(expected,actual,1e3);
    }

    @Test
    void calculateMonthlyPayment() {
        double expected = 2147.75;
        double actual = mortgageCalc.calculateMonthlyPayment();
        assertEquals(expected,actual, 1e3);
    }

    @Test
    void debtToIncomeRatioTest() {
        double expected = 23.06;
        double actual = mortgageCalc.calculateDebtToIncomeRatio();
        assertEquals(expected,actual,0.01);
    }

    @Test
    void evaluateDTITest(){
        String expected = "Favorable";
        String actual = mortgageCalc.evaluateDTI();
        assertEquals(expected,actual);
    }
}

