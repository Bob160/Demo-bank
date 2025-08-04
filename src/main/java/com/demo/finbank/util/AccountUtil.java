package com.demo.finbank.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Year;

@Configuration
public class AccountUtil {

    public static final String ACCOUNT_EXIST_CODE = "400";
    public static final String ACCOUNT_EXIST_MESSAGE = "User already has an account";

    public static final String ACCOUNT_DOES_NOT_EXIST_CODE = "402";
    public static final String ACCOUNT_DOES_NOT_EXIST_MESSAGE = "Account does not exist";

    public static String generateAccountNumber() {
//        2023 + randomSixDigits
        Year currentYear = Year.now();
        int minSix = 100000;
        int maxSix = 999999;

        //Generate random number between minSix and maxSix

        int randNumber = (int) Math.floor(Math.random() * (maxSix - minSix + 1) + minSix);

        //Convert currentYear and randomNumber to String and Concatenate them

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        System.out.println(year + randomNumber);
        return year + randomNumber;
    }
}
