package com.core.authserver.dto;
import lombok.*;
import java.util.List;

import javax.validation.constraints.NotEmpty;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class FilterUI{
     private String name;
     private String op;
     private List<String> values;
}
