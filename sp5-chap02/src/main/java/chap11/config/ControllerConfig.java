package chap11.config;

import chap11.changepwd.ChangePasswordService;
import chap11.controller.*;
import chap11.login.AuthService;
import chap11.spring.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    private MemberRegisterService memberRegSvc;
    private AuthService authService;
    private ChangePasswordService changePwdSvc;

    public ControllerConfig(MemberRegisterService memberRegSvc, AuthService authService, ChangePasswordService changePwdSvc) {
        this.memberRegSvc = memberRegSvc;
        this.authService = authService;
        this.changePwdSvc = changePwdSvc;
    }

    @Bean
    public RegisterController registerController() {
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegSvc);
        return controller;
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(authService);
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public ChangePwdController changePwdController() {
        return new ChangePwdController(changePwdSvc);
    }
}
