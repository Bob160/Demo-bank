package com.demo.finbank.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckNameResponse {
    private String accountName;
    private String status;
    private LocalDateTime createdAt;
}
