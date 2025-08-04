package com.demo.finbank.services;

import com.demo.finbank.dto.requests.CreditDebitRequest;
import com.demo.finbank.dto.requests.EnquiryRequestDto;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BalanceResponse;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.dto.responses.CheckNameResponse;

public interface UserService {
    BankResponse createAccount(UserRequestDto userRequestDto);

    BankResponse checkBalance(String accountNumber);

    String checkName(String accountNumber);
    //CheckNameResponse checkName(String accountNumber);

    BankResponse creditAccount(CreditDebitRequest creditRequest);


}
