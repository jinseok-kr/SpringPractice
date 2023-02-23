package chap11.login;

import chap11.spring.Member;
import chap11.spring.MemberDao;
import chap11.execption.WrongIdPasswordException;

public class AuthService {
    private MemberDao memberDao;

    public AuthService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public AuthInfo authenticate(String email, String password) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
         throw new WrongIdPasswordException();
        }
        if (!member.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }
}
