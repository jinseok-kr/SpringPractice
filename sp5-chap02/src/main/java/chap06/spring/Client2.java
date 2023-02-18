package chap06.spring;

public class Client2 {
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void connect() {
        System.out.println("Client.connect() 실행");
    }

    public void send() {
        System.out.println("Client.send() to " + host);
    }

    public void close() {
        System.out.println("Client.close() 실행");
    }
}
