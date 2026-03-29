package uz.hiwelcome.hiwelcome.res;


import lombok.*;
import uz.hiwelcome.hiwelcome.enums.Role;


import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String phoneNumber;
    private String email;
    private Set<Role> roles;
    private String token;
}
