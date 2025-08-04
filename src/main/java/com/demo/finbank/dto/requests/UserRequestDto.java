package com.demo.finbank.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    //private String customerIdentifier;
    private String firstname;
    private String othername;
    private String lastname;
    private String bvn;
    private String dob;
    //private String otherName;
    private int gender;
    private String address;
    //private String stateOfOrigin;
    //private String accountNumber;
    private String email;
    //private BigDecimal accountBalance;
    private String phoneNumber;
//    private String alternativePhoneNumber;
//    private String status;
}
