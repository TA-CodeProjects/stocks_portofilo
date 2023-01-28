package com.ta.stock_protfolio.beans;

import com.ta.stock_protfolio.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginParams {
    @ValidEmail
    private String email;
    @Size(min = 4, max = 20)
    private String password;
}
