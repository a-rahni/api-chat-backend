package fr.m2i.apichat.service;

import fr.m2i.apichat.exception.AlreadyExistsException;
import fr.m2i.apichat.model.User;
import fr.m2i.apichat.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//import static org.junit.jupiter.api.Assertions.*; to use assertJ
@ExtendWith(MockitoExtension.class)  // ca evite de OpenMocks, close ..
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private IUserService underTest;
   // AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        // initialisation all the mocks in this class // retourne un autoCloseable
        //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        // close the resources after each test
       // autoCloseable.close();
    }

    @Test
    void findAllTest() {
        //when
        underTest.findAll();

        //then
        // verfier que la methode findAll de user repository a été invoké (dans le service)
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    void AddUserSaveTest() {
        //given
        User user = new User();
        user.setUsername("user2");
        user.setEmail("emailUser2@gmail.com");
        user.setPassword("pwUser2");

        //when
        User saved = underTest.save(user);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenUserNameIsTaken() {
        //given
        User user = new User();
        user.setUsername("user2");
        user.setEmail("emailUser2@gmail.com");
        user.setPassword("pwUser2");

        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(new User(null,"user2","email","pw",null,null));

        //when
        //then
        assertThatThrownBy( ()-> underTest.save(user))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessageContaining("username or email already used");
        verify(userRepository, never()).save(any());

    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void delete() {
    }
}