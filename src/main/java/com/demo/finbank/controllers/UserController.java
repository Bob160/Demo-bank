package com.demo.finbank.controllers;

import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/create-account")
    public ResponseEntity<BankResponse> createAccount(@RequestBody UserRequestDto userRequestDto) {
        BankResponse bankResponse = userServiceImpl.createAccount(userRequestDto);

        if (bankResponse.getResponseCode().equals("200")) {
            System.out.println("Account name: " + bankResponse.getAccountInfo().getAccountName());
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bankResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
