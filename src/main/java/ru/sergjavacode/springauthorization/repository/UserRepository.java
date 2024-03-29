package ru.sergjavacode.springauthorization.repository;

import org.springframework.stereotype.Repository;
import ru.sergjavacode.springauthorization.model.Authorities;
import ru.sergjavacode.springauthorization.model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {
    private final Set<User> usersList = new HashSet<>(); //используем хэшсет для исключения колизий по имени пользователя

    public List<Authorities> getUserAuthorities(String user, String password) {
        testAddUser(); //временный метод, заполняющий репозиторий тестовым объектом User (для тестирования)
        Optional<User> currentUser = usersList.stream()
                .filter(x -> x.getUser().equals(user) && x.getPassword().equals(password))
                .collect(Collectors.toCollection(ArrayList::new))
                .stream().findFirst();  // если логин и пасс валидны, получаем соответствующий объект юзера
        return currentUser.orElse(new User("userEmpty", "passwordEmpty")).getAuthoritiesList();
    }

    //временный метод, заполняющий репозиторий тестовым объектом User (для тестирования)
    public void testAddUser() {
        User testUser =new User("userTest", "passwordTest");
        testUser.setAuthoritiesList(List.of(Authorities.READ,Authorities.DELETE));
        usersList.add(testUser);
    }
}