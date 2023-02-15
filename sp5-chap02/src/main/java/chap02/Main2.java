package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        //빈 객체는 기본적으로 싱글톤으로 생성됨
        //아래 코드 결과는 둘다 스프링, 안녕하세요.
        //일종의 포인터느낌으로 이해?
        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        g1.setFormat("%s, 반갑습니다.");
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        g2.setFormat("%s, 안녕하세요.");
        System.out.println("(g1 == g2) = " + (g1 == g2));
        System.out.println(g1.greet("스프링"));
        System.out.println(g2.greet("스프링"));
        ctx.close();
    }
}
