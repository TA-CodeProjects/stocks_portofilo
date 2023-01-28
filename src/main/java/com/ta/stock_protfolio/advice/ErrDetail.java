package com.ta.stock_protfolio.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrDetail {
    private String key;
    private String value;
}
