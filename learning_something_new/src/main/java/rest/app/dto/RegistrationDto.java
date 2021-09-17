package rest.app.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NonNull
    private String username;
    @NonNull
    private String password;
}
