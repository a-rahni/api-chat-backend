package fr.m2i.apichat.service;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.repository.CanalRepository;
import fr.m2i.apichat.repository.MessageRepository;
import fr.m2i.apichat.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.Date;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;//! fait gaffe bcp AsserThat
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Pour rendre le code plus clean (Virer AutoClosable)
@TestPropertySource(locations= "classpath:application-test.yml")
class CanalServiceTest {
    @Mock
    private CanalRepository canalRepository;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserRepository userRepository;
   // private AutoCloseable autoCloseable;
    private CanalService underTest;
    @BeforeEach
    void setUp() {
       // autoCloseable = MockitoAnnotations.openMocks(this);
        underTest=new CanalService(userRepository,canalRepository,messageRepository);

    }
   /* @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();//Fermer chaque instance par test
   }*/

    @Test
    void addMessageToCanal() {
    }

    @Test
    void getCanals() {
        //when
        underTest.getCanals();
        //Then
        verify(canalRepository).findAll();//Verifier que findAll() fonctionne
    }

    @Test
    void deleteCanal() {
    }

    @Test
    void findByCanals() {
    }

    @Test
    void findById() {
    }

    @Test
    void canAddCanal() throws Throwable {
        //Given
        Canal canal = new Canal(null, "Room2", "desc Room2 ", new Date(), new Date(), null, null);
        //When
        underTest.addCanal(canal);
        //Then  //capturer la valeur à sauvegarder
        ArgumentCaptor<Canal> canalArgumentCaptor=
                ArgumentCaptor.forClass(Canal.class);
        verify(canalRepository).save(canalArgumentCaptor.capture());
        Canal capturedCanal=canalArgumentCaptor.getValue();
        assertThat(capturedCanal).isEqualTo(canal);
    }
    @Test
    void willThrowWhenCanalIsTaken() {
        //Given
        Canal canal = new Canal(null, "Room2", "desc Room2 ", new Date(), new Date(), null, null);
        canalRepository.save(canal);
        Canal canal2 = new Canal(null, "Room2", "desc Room2 ", new Date(), new Date(), null, null);
        //when
        //then
        assertThatThrownBy(()->underTest.addCanal(canal2))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(String.format("Canal: %s existe déjà!",canal2.getName()));

    }

    @Test
    void updateCanal() {
    }
}
