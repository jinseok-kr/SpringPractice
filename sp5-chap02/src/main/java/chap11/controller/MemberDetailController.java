package chap11.controller;

import chap11.execption.MemberNotFoundException;
import chap11.spring.Member;
import chap11.spring.MemberDao;
import org.springframework.beans.TypeMismatchException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class MemberDetailController {

    private MemberDao memberDao;

    public MemberDetailController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/member/{id}")
    public String detail(@PathVariable("id") Long memId, Model model) {
        Member member = memberDao.selectById(memId);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "member/invalidId";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleNotFoundException() {
        return "member/noMember";
    }
}
