package com.demo.finbank.controllers;

import com.demo.finbank.dto.requests.CreditDebitRequest;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.dto.responses.CheckNameResponse;
import com.demo.finbank.model.Users;
import com.demo.finbank.serviceImpl.UserServiceImpl;
import com.demo.finbank.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    //Create an account
    @PostMapping("/create-account")
    public ResponseEntity<BankResponse> createAccount(@RequestBody UserRequestDto userRequestDto) {
        BankResponse bankResponse = userServiceImpl.createAccount(userRequestDto);

        if (bankResponse.getResponseCode().equals("200")) {
            //System.out.println("Account name: " + bankResponse.getAccountInfo().getAccountName());
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bankResponse, HttpStatus.BAD_REQUEST);
        }
    }

    //Check account balance
    @GetMapping("/check-balance")
    public ResponseEntity<BankResponse> checkBalance(@RequestParam String accountNumber) {
        BankResponse bankResponse = userServiceImpl.checkBalance(accountNumber);

        if (bankResponse.getResponseCode().equals("200")) {
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
        }

    }

    //Check account name
//    @GetMapping("/check-name")
//    public ResponseEntity<BankResponse> checkName(@RequestParam String accountNumber) {
//        String response = userServiceImpl.checkName(accountNumber);
//
//        if (response != AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE) {
//            BankResponse bankResponse = BankResponse.builder()
//                    .responseCode("200")
//                    .responseMessage(response)
//                    .build();
//
//            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
//        } else {
//            BankResponse bankResponse = BankResponse.builder()
//                    .responseCode("400")
//                    .responseMessage("Failed")
//                    .build();
//            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/check-name")
    public ResponseEntity<BankResponse> checkName (@RequestParam String accountNumber) {
        //CheckNameResponse checkNameResponse= userServiceImpl.checkName(accountNumber);
        String usersName = userServiceImpl.checkName(accountNumber);

        //if (checkNameResponse.getAccountName().equals("Account does not exist")) {
        if (usersName.equals("Account does not exist")) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode("400")
                    .responseMessage("Account does not exist")
                    .build();
            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
        } else {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode("200")
                    .responseMessage(usersName)
                    .build();
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        }

    }

    @PostMapping("/credit-account")
    public ResponseEntity<BankResponse> creditAccount(@RequestBody @Valid CreditDebitRequest creditRequest) {
        BankResponse bankResponse = userServiceImpl.creditAccount(creditRequest);
        System.out.println("This is the bank response from the db: " + bankResponse);

        if (bankResponse.getResponseMessage().equals ("Account does not exist")) {
//            BankResponse bankResponse = BankResponse.builder()
//                    .responseCode("400")
//                    .responseMessage("Account does not exist")
//                    .build();
            bankResponse.setResponseCode("400");
            bankResponse.setResponseMessage("Account does not exist");
            System.out.println("Account does not exist");
            return new ResponseEntity<>(bankResponse, HttpStatus.NOT_FOUND);
        } else {
//            BankResponse bankResponse = BankResponse.builder()
//                    .responseCode("200")
//                    .responseMessage("Account exist")
//                    .build();
            bankResponse.setResponseMessage("Account credited successfully");
            bankResponse.setResponseCode("200");
            return new ResponseEntity<>(bankResponse, HttpStatus.OK);
        }
    }
}
