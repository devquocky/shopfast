package personal.shopfast.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerFilter {
    private String field;
    private String operator;
    private String value;
    private List<String> values;
}
