package com.demo.finbank.services;

import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;

public interface UserService {
    BankResponse createAccount(UserRequestDto userRequestDto);


}
