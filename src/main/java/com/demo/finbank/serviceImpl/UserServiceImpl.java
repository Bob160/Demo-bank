//package com.demo.finbank.serviceImpl;
//
//import com.demo.finbank.dto.requests.UserRequestDto;
//import com.demo.finbank.dto.responses.BankResponse;
//import com.demo.finbank.model.User;
//import com.demo.finbank.services.UserService;
//import com.demo.finbank.util.AccountUtil;
//
//import java.math.BigDecimal;
//
//public class UserServiceImpl implements UserService {
//    @Override
//    public BankResponse createAccount(UserRequestDto userRequestDto) {
//        /**
//         * Check if User already exist else create new user
//         */
//
//        User newUser =
//        /*
//         * Create account and save a new user
//         */
//        User newUser = User.builder()
//                .firstname(userRequestDto.getFirstname())
//                .lastname(userRequestDto.getLastname())
//                .otherName(userRequestDto.getAddress())
//                .gender(userRequestDto.getGender())
//                .stateOfOrigin(userRequestDto.getStateOfOrigin())
//                .accountNumber(AccountUtil.generateAccountNumber())
//                .accountBalance(BigDecimal.ZERO)
//                .email(userRequestDto.getEmail())
//                .phoneNumber(userRequestDto.getPhoneNumber())
//                .alternativePhoneNumber(userRequestDto.getAlternativePhoneNumber())
//                .status("ACTIVE")
//                .build();
//
//        return
//    }
//}
