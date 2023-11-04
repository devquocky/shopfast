package personal.shopfast.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    private int page;
    private int size;
    private int totalElements;
    private T result;
}
