package chap11.spring;

import chap11.execption.WrongIdPasswordException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
