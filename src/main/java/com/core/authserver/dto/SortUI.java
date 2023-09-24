package com.core.authserver.dto;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SortUI{
    private String column;
    private String direction;
}