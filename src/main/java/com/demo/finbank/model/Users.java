package com.demo.finbank.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "First name is mandatory")
    private String first_name;
    @NotBlank(message = "Other name is mandatory")
    private String other_name;
    @NotBlank(message = "Last name is mandatory")
    private String last_name;
    @NotBlank(message = "Mobile number is mandatory")
    @Size(min = 11, max = 11)
    private String mobile_num;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "BVN is mandatory")
    @Size(min = 11, max = 11)
    private String bvn;
    @NotBlank(message = "Date of birth is mandatory")
    private String dob;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "Gender is mandatory")
    private int gender;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    private BigDecimal accountBalance;
    private String accountNumber;
    private String status;
}
