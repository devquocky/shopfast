package personal.shopfast.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.shopfast.constant.TokenType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String refreshToken;
    private String tokenType;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
