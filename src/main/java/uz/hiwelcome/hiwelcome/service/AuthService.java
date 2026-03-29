package uz.hiwelcome.hiwelcome.service;

import org.springframework.stereotype.Service;
import uz.hiwelcome.hiwelcome.config.payload.base.ResBaseMsg;
import uz.hiwelcome.hiwelcome.req.*;
import uz.hiwelcome.hiwelcome.res.LoginResponse;


@Service
public interface AuthService {

    ResBaseMsg signUp(SignUpRequest request);

    LoginResponse signIn(SignInRequest request);

    ResBaseMsg forgotPassword(SendCodeRequest request);

    LoginResponse verify(VerifyRequest request);

    ResBaseMsg checkForgotPassword(ForgotPasswordRequest request);

    ResBaseMsg resendCode(SendCodeRequest request);



}
