package com.demo.finbank.serviceImpl;

import com.demo.finbank.dto.AccountInfo;
import com.demo.finbank.dto.requests.CreditDebitRequest;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.dto.responses.CheckNameResponse;
import com.demo.finbank.model.Users;
import com.demo.finbank.repositories.UserRepository;
import com.demo.finbank.services.UserService;
import com.demo.finbank.util.AccountUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
//@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountUtil accountUtil;

//    @Autowired
//    EmailService emailService;
//    @Autowired
//    EmailServiceImpl emailService;

    @Override
    public BankResponse createAccount(UserRequestDto userRequestDto) {


        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtil.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtil.ACCOUNT_EXIST_MESSAGE)
                    .build();
        } else {
            Users newUsers = Users.builder()
                    .first_name(userRequestDto.getFirstname())
                    .other_name(userRequestDto.getOthername())
                    .last_name(userRequestDto.getLastname())
                    .bvn(userRequestDto.getBvn())
                    .email(userRequestDto.getEmail())
                    .address(userRequestDto.getAddress())
                    .gender(userRequestDto.getGender())
                    .mobile_num(userRequestDto.getPhoneNumber())
                    .dob(userRequestDto.getDob())
                    //.id(userRequestDto.getCustomerIdentifier())
//                    .firstname(userRequestDto.getFirstname())
//                    .lastname(userRequestDto.getLastname())
//                    .otherName(userRequestDto.getOtherName())
//                    .address(userRequestDto.getAddress())
//                    .gender(userRequestDto.getGender())
//                    .stateOfOrigin(userRequestDto.getStateOfOrigin())
//                    .accountNumber(AccountUtil.generateAccountNumber())
//                    .accountBalance(BigDecimal.ZERO)
//                    .email(userRequestDto.getEmail())
//                    .phoneNumber(userRequestDto.getPhoneNumber())
//                    .alternativePhoneNumber(userRequestDto.getAlternativePhoneNumber())
                    .status("ACTIVE")
                    .accountBalance(BigDecimal.ZERO)
                    //.accountNumber(newUsers.getAccountNumber())
                    .accountNumber(AccountUtil.generateAccountNumber())
                    .build();
            System.out.println("This is the new user: " + newUsers);
            userRepository.save(newUsers);
//            EmailDetails emailDetails = EmailDetails.builder()
//                    .recipient(newUser.getEmail())
//                    .subject("ACCOUNT CREATION")
//                    .messageBody("Congratulations, \n" +
//                            "Your account has been created successfully. Your account details are as follows: \n" +
//                    "Account Name: " + newUser.getFirstname() + " " + newUser.getLastname() + " " + "and your account number is: " + newUser.getAccountNumber())
//
//                    .build();
//            emailService.sendEmailAlert(emailDetails);
            return BankResponse.builder()
                    .responseMessage("Account created successfully!")
                    .responseCode("200")
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(String.valueOf(newUsers.getAccountBalance()))
                            //.accountNumber(newUsers.getAccountNumber())
                            .accountNumber(newUsers.getAccountNumber())
                            .accountName(newUsers.getFirst_name() + " " + newUsers.getOther_name()+ " " + newUsers.getLast_name())
                            .build())
                    .build();
        }
    }

    @Override
    public BankResponse checkBalance(String accountNumber) {
        return null;
    }

//    @Override
//    public CheckNameResponse checkName(String accountNumber) {
//        return null;
//    }

//    @Override
//    public BankResponse creditAccount(CreditDebitRequest creditRequest) {
//        return null;
//    }

//    @Override
//    public BankResponse checkBalance(String accountNumber) {
//        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);
//
//        if (!isAccountExist) {
//            return BankResponse.builder()
//                    .responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
//                    .responseMessage(AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE)
//                    .build();
//        } else {
//            User foundUser = userRepository.findByAccountNumber(accountNumber);
//            return BankResponse.builder()
//                    .responseMessage("Success")
//                    .responseCode("200")
//                    .accountInfo(AccountInfo.builder()
//                            .accountName(foundUser.getFirstname() + " " + foundUser.getLastname())
//                            .accountNumber(accountNumber)
//                            .accountBalance(String.valueOf(foundUser.getAccountBalance()))
//                            .build())
//                    .build();
//
//        }
//
//    }

//    @Override
//    public CheckNameResponse checkName(String accountNumber) {
//        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);
//
//        if (!isAccountExist) {
//          return  CheckNameResponse.builder()
//                  .accountName(AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE)
//                  .status("Account does not exist")
//                    .build();
//        } else {
//            User foundUser = userRepository.findByAccountNumber(accountNumber);
//            return CheckNameResponse.builder()
//                    .accountName(foundUser.getFirstname() + " " + foundUser.getOtherName() + " " + foundUser.getLastname())
//                    .createdAt(foundUser.getCreatedAt())
//                    .status(foundUser.getStatus())
//                    .build();
//        }
//    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditRequest) {
        //Check if the account exist
        boolean isAccountExist = userRepository.existsByAccountNumber(creditRequest.getAccountNumber());
        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
                    .responseMessage(AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE)
                    .build();
        } else {
            Users foundUser = userRepository.findByAccountNumber(creditRequest.getAccountNumber());
            foundUser.setAccountBalance((foundUser.getAccountBalance().add(BigDecimal.valueOf(creditRequest.getAmount()))));
                    //foundUser.getAccountBalance()creditRequest.getAmount());
            userRepository.save(foundUser);

            return BankResponse.builder()
                    .responseCode("200")
                    .responseMessage("Account credited successfully")
                    .accountInfo(AccountInfo.builder()
                            .accountName(foundUser.getFirst_name() + " " + foundUser.getOther_name() + " " + foundUser.getLast_name())
                            .accountBalance(String.valueOf(foundUser.getAccountBalance()))
                            .accountNumber(creditRequest.getAccountNumber())
                            .build())
                    .build();
        }
    }

    @Override
    public String checkName(String accountNumber) {

        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);

        if (!isAccountExist) {
            return AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE;
        } else {
            Users foundUser = userRepository.findByAccountNumber(accountNumber);
            BankResponse.builder()
                    .accountInfo(
                            AccountInfo.builder()
                                    .accountNumber(accountNumber)
                                    .accountBalance(String.valueOf(foundUser.getAccountBalance()))
                                    .accountName(foundUser.getFirst_name() + " " + foundUser.getOther_name() + " " + foundUser.getLast_name())
                                    .build()
                    )
                    .responseMessage("Account Found!")
                    .responseCode("200")
                    .build();

            return foundUser.getFirst_name() + " " + foundUser.getOther_name() + " " + foundUser.getLast_name();
        }
    }
}
