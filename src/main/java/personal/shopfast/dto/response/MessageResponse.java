package personal.shopfast.dto.response;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    @NonNull
    private String message;

    private Object reponseObject;
}
