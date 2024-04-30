package personal.shopfast.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import personal.shopfast.constant.TokenType;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class TokenRefreshResponse {
    @NonNull
    private String accessToken;

    @NonNull
    private String refreshToken;

    private String tokenType = TokenType.BEARER;
}
