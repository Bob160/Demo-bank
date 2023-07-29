package com.demo.finbank.controllers;

import com.demo.finbank.dto.AccountInfo;
import com.demo.finbank.dto.requests.EnquiryRequestDto;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.serviceImpl.UserServiceImpl;
import com.demo.finbank.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/check-balance")
    public ResponseEntity<BankResponse> checkBalance(@RequestParam String accountNumber) {
        BankResponse bankResponse = userServiceImpl.checkBalance(accountNumber);

        if (bankResponse.getResponseCode().equals("200")) {
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/check-name")
    public ResponseEntity<BankResponse> checkName(@RequestParam String accountNumber) {
        String response = userServiceImpl.checkName(accountNumber);

        if (response != AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode("200")
                    .responseMessage(response)
                    .build();

            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        } else {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode("400")
                    .responseMessage("Failed")
                    .build();
            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
        }
    }
}
