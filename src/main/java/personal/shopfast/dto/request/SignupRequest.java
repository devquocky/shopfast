package personal.shopfast.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import personal.shopfast.util.annotation.PasswordMatching;

import javax.validation.constraints.Email;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatching(
        newPassword = "password",
        oldPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
public class SignupRequest {

    private String username;

    private String password;

    private String confirmPassword;

    @Email
    private String email;

    private Set<Long> roleIds;
}
