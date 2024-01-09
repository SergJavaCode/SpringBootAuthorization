package ru.sergjavacode.springauthorization.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class User {

    @NotBlank
    @Size(min = 3, max = 20, message
            = "User name must be between 3 and 20 characters")
    private String user;
    @NotBlank
    @Size(min = 8, max = 20, message
            = "User name must be between 8 and 20 characters")
    private String password;
    private List<Authorities> authoritiesList;
    public User(String userName, String password) {
        this.user = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user, user.user) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "authoritiesList=" + authoritiesList +
                ", userName='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
