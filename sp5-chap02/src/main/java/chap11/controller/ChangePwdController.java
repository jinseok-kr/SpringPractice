package chap11.controller;

import chap11.changepwd.ChangePasswordService;
import chap11.changepwd.ChangePwdCommand;
import chap11.changepwd.ChangePwdCommandValidator;
import chap11.execption.WrongIdPasswordException;
import chap11.login.AuthInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

    private ChangePasswordService changePasswordService;

    public ChangePwdController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @GetMapping
    public String form (@ModelAttribute("command") ChangePwdCommand pwdCmd, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo == null) {
            return "redirect:/login";
        }
        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit (@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, HttpSession session) {
        new ChangePwdCommandValidator().validate(pwdCmd, errors);
        if (errors.hasErrors()) {
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try {
            changePasswordService.changePassword(authInfo.getEmail(), pwdCmd.getCurrentPassword(), pwdCmd.getNewPassword());
            return "edit/changePwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }
}
