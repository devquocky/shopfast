package personal.shopfast.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private boolean isDeleted;
}
