package com.demo.finbank.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreditDebitRequest {
    private String accountNumber;
    private BigDecimal amount;
}
