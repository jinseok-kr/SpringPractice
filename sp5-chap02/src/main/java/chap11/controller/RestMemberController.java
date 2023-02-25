package chap11.controller;

import chap11.execption.DuplicateMemberException;
import chap11.execption.MemberNotFoundException;
import chap11.response.ErrorResponse;
import chap11.spring.Member;
import chap11.spring.MemberDao;
import chap11.spring.MemberRegisterService;
import chap11.spring.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestMemberController {
    private MemberDao memberDao;
    private MemberRegisterService memberRegSvc;

    public RestMemberController(MemberDao memberDao, MemberRegisterService memberRegSvc) {
        this.memberDao = memberDao;
        this.memberRegSvc = memberRegSvc;
    }

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberDao.selectAll();
    }

//    @GetMapping("/api/members/{id}")
//    public Member member(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        Member member = memberDao.selectById(id);
//        if (member == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return null;
//        }
//        return member;
//    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable Long id) throws IOException {
        Member member = memberDao.selectById(id);
        if (member == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
            throw new MemberNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

//    @PostMapping("/api/members")
//    public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {
//        try {
//            Long newMemberId = memberRegSvc.regist(regReq);
//            response.setHeader("Location", "/api/members/" + newMemberId);
//            response.setStatus(HttpServletResponse.SC_CREATED);
//        } catch (DuplicateMemberException dupEx) {
//            response.sendError(HttpServletResponse.SC_CONFLICT);
//        }
//    }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, Errors errors) throws IOException {
        if (errors.hasErrors()) {
            String errorCodes = errors.getAllErrors()
                    .stream()
                    .map(error -> error.getCodes()[0])
                    .collect(Collectors.joining(","));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("errorCodes = " + errorCodes));
        }
        try {
            Long newMemberId = memberRegSvc.regist(regReq);
            URI uri = URI.create("/api/members/" + newMemberId);
            return ResponseEntity.created(uri).build();
        } catch (DuplicateMemberException dupEx) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
