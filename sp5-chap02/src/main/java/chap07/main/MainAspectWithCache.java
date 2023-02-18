package chap07.main;

import chap07.calculator.Calculator;
import chap07.config.AppCtxWithCache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspectWithCache {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        //calculator 빈은 실제로는  cacheaspect  프록시 객체이다. 근데 cacheaspect 프록시 객체의 대상 객체는 exetimeaspect의 프록시객체이다. 그리고 exe 프록시의 대상 객체가 실제 대상 객체이다.
        // (cacheaspect 프록시) -> (exe프록시) -> (실제대상객체)
        cal.factorial(7);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);
        ctx.close();
    }
}
