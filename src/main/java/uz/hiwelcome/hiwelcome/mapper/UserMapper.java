package uz.hiwelcome.hiwelcome.mapper;





import uz.hiwelcome.hiwelcome.authEntity.User;
import uz.hiwelcome.hiwelcome.res.LoginResponse;

import java.util.Collections;

public interface UserMapper {

    static LoginResponse toLoginResponse(User user, String token) {
        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(Collections.singleton(user.getRole()))
                .token(token)
                .build();
    }


}
