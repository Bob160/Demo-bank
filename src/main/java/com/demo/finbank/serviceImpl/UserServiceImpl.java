package com.demo.finbank.serviceImpl;

import com.demo.finbank.dto.AccountInfo;
import com.demo.finbank.dto.EmailDetails;
import com.demo.finbank.dto.requests.EnquiryRequestDto;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.dto.responses.CheckNameResponse;
import com.demo.finbank.model.User;
import com.demo.finbank.repositories.UserRepository;
import com.demo.finbank.services.EmailService;
import com.demo.finbank.services.UserService;
import com.demo.finbank.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
//    @Autowired
//    EmailService emailService;
    @Autowired
    EmailServiceImpl emailService;
    @Override
    public BankResponse createAccount(UserRequestDto userRequestDto) {


        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtil.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtil.ACCOUNT_EXIST_MESSAGE)
                    .build();
        } else {
            User newUser = User.builder()
                    .firstname(userRequestDto.getFirstname())
                    .lastname(userRequestDto.getLastname())
                    .otherName(userRequestDto.getOtherName())
                    .address(userRequestDto.getAddress())
                    .gender(userRequestDto.getGender())
                    .stateOfOrigin(userRequestDto.getStateOfOrigin())
                    .accountNumber(AccountUtil.generateAccountNumber())
                    .accountBalance(BigDecimal.ZERO)
                    .email(userRequestDto.getEmail())
                    .phoneNumber(userRequestDto.getPhoneNumber())
                    .alternativePhoneNumber(userRequestDto.getAlternativePhoneNumber())
                    .status("ACTIVE")
               .build();
            userRepository.save(newUser);
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(newUser.getEmail())
                    .subject("ACCOUNT CREATION")
                    .messageBody("Congratulations, \n" +
                            "Your account has been created successfully. Your account details are as follows: \n" +
                    "Account Name: " + newUser.getFirstname() + " " + newUser.getLastname() + " " + "and your account number is: " + newUser.getAccountNumber())

                    .build();
            emailService.sendEmailAlert(emailDetails);
            return BankResponse.builder()
                    .responseMessage("Account created successfully!")
                    .responseCode("200")
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(String.valueOf(BigDecimal.ZERO))
                            .accountNumber(newUser.getAccountNumber())
                            .accountName(newUser.getFirstname() + " " + newUser.getOtherName() + " " + newUser.getLastname())
                            .build())
                    .build();
        }
    }

    @Override
    public BankResponse checkBalance(String accountNumber) {
        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);

        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
                    .responseMessage(AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE)
                    .build();
        } else {
            User foundUser = userRepository.findByAccountNumber(accountNumber);
            return BankResponse.builder()
                    .responseMessage("Success")
                    .responseCode("200")
                    .accountInfo(AccountInfo.builder()
                            .accountName(foundUser.getFirstname() + " " + foundUser.getLastname())
                            .accountNumber(accountNumber)
                            .accountBalance(String.valueOf(foundUser.getAccountBalance()))
                            .build())
                    .build();

        }

    }

    @Override
    public CheckNameResponse checkName(String accountNumber) {
        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);

        if (!isAccountExist) {
          return  CheckNameResponse.builder()
                  .accountName(AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE)
                  .status("Account does not exist")
                    .build();
        } else {
            User foundUser = userRepository.findByAccountNumber(accountNumber);
            return CheckNameResponse.builder()
                    .accountName(foundUser.getFirstname() + " " + foundUser.getOtherName() + " " + foundUser.getLastname())
                    .createdAt(foundUser.getCreatedAt())
                    .status(foundUser.getStatus())
                    .build();
        }
    }

//    @Override
//    public String checkName(String accountNumber) {
//
//        boolean isAccountExist = userRepository.existsByAccountNumber(accountNumber);
//
//        if (!isAccountExist) {
//            return AccountUtil.ACCOUNT_DOES_NOT_EXIST_MESSAGE;
//        } else {
//            User foundUser = userRepository.findByAccountNumber(accountNumber);
////            BankResponse.builder()
////                    .accountInfo(
////                            AccountInfo.builder()
////                                    .accountNumber(enquiryRequestDto.getAccountNumber())
////                                    .accountBalance(String.valueOf(foundUser.getAccountBalance()))
////                                    .accountName(foundUser.getFirstname() + " " + foundUser.getOtherName() + " " + foundUser.getLastname())
////                                    .build()
////                    )
////                    .responseMessage("Account Found!")
////                    .responseCode("200")
////                    .build();
//
//            return foundUser.getFirstname() + " " + foundUser.getOtherName() + " " + foundUser.getLastname();
//        }
//    }
}
