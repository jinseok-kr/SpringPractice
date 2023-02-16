package chap04.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                    "회원정보: 아이디= %d, 이메일= %s, 이름= %s, 등록일=%tF\n",
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    member.getRegisterDateTime()
            );
        } else {
            System.out.printf(
                    "회원정보: 아이디= %d, 이메일= %s, 이름= %s, 등록일=%s\n",
                    member.getId(),
                    member.getEmail(),
                    member.getName(),
                    dateTimeFormatter.format(member.getRegisterDateTime())
            );
        }
    }

    //required false의 경우 DateTimeFormatter(빈) 못찾으면 아예 얘를 안부름 -> 위의 생성자 값 초기화 x
    //optional이나 nullable은 DateTimeFormatter(빈) 못찾으면 얘한테 null 전달 -> 위의 생성자 값 초기화 o
    @Autowired(required = false)
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
