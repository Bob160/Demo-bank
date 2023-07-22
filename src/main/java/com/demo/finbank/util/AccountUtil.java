package com.demo.finbank.util;

import java.time.Year;

public class AccountUtil {



    public static String generateAccountNumber() {
        /** 2023 + randomSixDigits
         *
         */

        Year currentYear = Year.now();
        int minSix = 100000;
        int maxSix = 999999;

        //Generate random nuber between minSix and maxSix

        int randNumber = (int) Math.floor(Math.random() * (maxSix - minSix + 1) + minSix);

        //Convert currentYear and randomNumber to String and Concatenate them

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        System.out.println(year + randomNumber);
        return year + randomNumber;
    }
}
