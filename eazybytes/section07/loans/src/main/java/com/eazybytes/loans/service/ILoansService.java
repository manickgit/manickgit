package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {


    /**
     * Create a loan based on given mobile number
     *
     * @param mobileNumber
     *      Mobile number
     */
    void createLoan(String mobileNumber);

    /**
     * Fetch loan details based on a given mobile number.
     * @param mobileNumber
     *      The mobile number
     * @return boolean
     *      - status of the action
     *
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto
     * @return
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteLoan(String mobileNumber);
}
