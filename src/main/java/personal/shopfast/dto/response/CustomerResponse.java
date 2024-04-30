package personal.shopfast.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
