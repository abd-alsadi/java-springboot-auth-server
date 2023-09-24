package com.core.authserver.responses;

import com.core.authserver.constants.ModConstant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class FilterDataResponse {
    private String message;
    private Object data;
    private int count;
    private  ModConstant.StatusCode statusCode;
}
