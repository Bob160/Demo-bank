package com.demo.finbank.serviceImpl;

import com.demo.finbank.dto.AccountInfo;
import com.demo.finbank.dto.requests.UserRequestDto;
import com.demo.finbank.dto.responses.BankResponse;
import com.demo.finbank.model.User;
import com.demo.finbank.repositories.UserRepository;
import com.demo.finbank.services.UserService;
import com.demo.finbank.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public BankResponse createAccount(UserRequestDto userRequestDto) {
        /**
         * Check if User already exist else create new user
         */

        User user;
        /*
         * Create account and save a new user
         */
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            BankResponse bankResponse = BankResponse.builder()
                    .responseCode(AccountUtil.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtil.ACCOUNT_EXIST_MESSAGE)
                    .build();
            return bankResponse;
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
}
