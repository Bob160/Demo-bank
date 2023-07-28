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
    private String firstname;
    private String lastname;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;
    //private String accountNumber;
    private String email;
    //private BigDecimal accountBalance;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String status;
}
