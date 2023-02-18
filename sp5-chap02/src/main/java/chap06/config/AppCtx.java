package chap06.config;

import chap06.spring.Client;
import chap06.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx {

    @Bean
    @Scope("prototype") // 빈 생성, 프로퍼티 설정, 초기화 진행 o -> 소멸 진행 x == 소멸처리를 코드에서 실행할것
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        //client.afterPropertiesSet(); -> 이미 bean 등록할 때 하는데 한번 더 넣을필요없음 주의!!
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    @Scope("singleton")
    public Client2 client2() {
        Client2 client2 = new Client2();
        client2.setHost("host");
        return client2;
    }
}
