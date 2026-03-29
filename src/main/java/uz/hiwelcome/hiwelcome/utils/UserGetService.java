package uz.hiwelcome.hiwelcome.utils;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.hiwelcome.hiwelcome.authEntity.User;
import uz.hiwelcome.hiwelcome.config.UserPrincipal;


import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Component
public class UserGetService {

    public static User getUser() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || authentication.getPrincipal().toString().equals("anonymousUser"))
            throw new AccessDeniedException("Access denied");

        return ((UserPrincipal) authentication.getPrincipal()).user();
    }
}


