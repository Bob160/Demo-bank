package com.demo.finbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountInfo {
    private String accountName;
    private String accountNumber;
    private String accountBalance;
}
