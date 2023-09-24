package com.core.authserver.requests;
import com.core.authserver.dto.FilterUI;
import com.core.authserver.dto.SortUI;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class FilterDataRequest {
    private List<FilterUI> filters;
    private SortUI sort;
    private int limit;
    private int page;
}

