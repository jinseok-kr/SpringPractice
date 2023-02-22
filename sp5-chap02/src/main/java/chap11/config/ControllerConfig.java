package chap11.config;

import chap11.controller.RegisterController;
import chap11.controller.SurveyController;
import chap11.spring.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    private MemberRegisterService memberRegSvc;

    public ControllerConfig(MemberRegisterService memberRegSvc) {
        this.memberRegSvc = memberRegSvc;
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
}
