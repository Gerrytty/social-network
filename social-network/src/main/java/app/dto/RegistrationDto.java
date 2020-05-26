package app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDto {

    @Size(min = 1, message = "Should not be empty")
    private String firstName;

    @Size(min = 1, message = "Should not be empty")
    private String secondName;

    @Size(min = 1, message = "Should not be empty")
    @Email(message = "Email is not correct")
    private String email;

    @Size(min = 5, message = "Should not be less then 5 symbols")
    private String password;

}
