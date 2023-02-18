package chap06.main;

import chap06.config.AppCtx;
import chap06.spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        //bean 등록 시 이름으로 호출가능
        Client client = (Client)ctx.getBean("client");
        client.send();

        ctx.close();
    }
}
