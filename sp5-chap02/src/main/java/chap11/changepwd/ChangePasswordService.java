package chap11.changepwd;

import chap11.execption.MemberNotFoundException;
import chap11.spring.Member;
import chap11.spring.MemberDao;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
    private MemberDao memberDao; 

    public ChangePasswordService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }
}
