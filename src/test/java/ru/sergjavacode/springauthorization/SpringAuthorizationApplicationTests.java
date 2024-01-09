package ru.sergjavacode.springauthorization;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.sergjavacode.springauthorization.controllers.AuthorizationController;
import ru.sergjavacode.springauthorization.exceptions.InvalidCredentials;
import ru.sergjavacode.springauthorization.exceptions.UnauthorizedUser;
import ru.sergjavacode.springauthorization.model.Authorities;
import ru.sergjavacode.springauthorization.model.User;
import ru.sergjavacode.springauthorization.repository.UserRepository;
import ru.sergjavacode.springauthorization.service.AuthorizationService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringAuthorizationApplicationTests {
    @Resource

    @Test
    void contextLoads(ApplicationContext context) { //проверяем, что контекст приложения загружен правильно и содержит наши необходимые бины
        assertThat(context.getBean(AuthorizationController.class)).isNotNull();
        assertThat(context.getBean(AuthorizationService.class)).isNotNull();
        assertThat(context.getBean(UserRepository.class)).isNotNull();
    }

    @Test
    void invalidCredentialsExceptionalTest(ApplicationContext context) { //проверяем тип выбрасываемого исключения при исключительной ситуации
        Exception exception = Assertions.assertThrows(InvalidCredentials.class, () -> context.getBean(AuthorizationService.class).getAuthorities(new User("", "")));
        Assertions.assertEquals(InvalidCredentials.class, exception.getClass());
    }

    @Test
    void unauthorizedUserExceptionalTest(ApplicationContext context) {//проверяем тип выбрасываемого исключения при исключительной ситуации
        Exception exception = Assertions.assertThrows(UnauthorizedUser.class, () -> context.getBean(AuthorizationService.class).getAuthorities(new User("UserException", "PasswordException")));
        Assertions.assertEquals(UnauthorizedUser.class, exception.getClass());
    }

    @Test
    void simpleUserPasswordTest() { //тест через рефлексию
        UserRepository userRepository = new UserRepository();
        Set<User> usersListTest = new HashSet<>();
        List<Authorities> authoritiesListTest = new ArrayList<>();
        authoritiesListTest.add(Authorities.READ);
        authoritiesListTest.add(Authorities.DELETE);
        User testUser = new User("testName", "testPassword");
        testUser.setAuthoritiesList(authoritiesListTest);
        usersListTest.add(testUser);
        try {
            Field field = userRepository.getClass().getDeclaredField("usersList"); //устанавливаем значение приватного поля через рефлексию
            field.setAccessible(true);
            field.set(userRepository, usersListTest);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        List<Authorities> assertionsListTest = new ArrayList<>();
        assertionsListTest.add(Authorities.READ);
        assertionsListTest.add(Authorities.DELETE);
        Assertions.assertEquals(assertionsListTest, userRepository.getUserAuthorities("testName", "testPassword"));
    }

}
