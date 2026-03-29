package uz.hiwelcome.hiwelcome.res;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.hiwelcome.hiwelcome.authEntity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    private Long expiresIn;
    private String tokenType;
    private User user;
}