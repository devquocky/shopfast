package personal.shopfast.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    //    @NotEmpty(message = "Customer's username can not empty")
    private String username;

    //    @NotEmpty(message = "Customer's password can not empty")
    private String password;

    //    @NotEmpty(message = "Customer's first name can not empty")
    private String firstName;

    //    @NotEmpty(message = "Customer's last name can not empty")
    private String lastName;

    //    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")
    private String phoneNumber;
}
