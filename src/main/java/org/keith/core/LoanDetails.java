package org.keith.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PROTECTED)
class LoanDetails {
    private Integer terms; // Terms of Loan
    private Double annualInterestRate;
    private Integer principle; // Loan amount
    private Integer numberPayment; // Number of payments made every single year
    private Integer grossIncome;
}
