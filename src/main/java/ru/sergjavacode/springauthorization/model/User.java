package ru.sergjavacode.springauthorization.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class User {

    private List<Authorities> authoritiesList;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public User(String userName, String password, List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "authoritiesList=" + authoritiesList +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
