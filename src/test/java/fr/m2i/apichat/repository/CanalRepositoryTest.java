package fr.m2i.apichat.repository;
import fr.m2i.apichat.model.Canal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@TestPropertySource(locations= "classpath:application-test.yml")
class CanalRepositoryTest {
    @Autowired
    private CanalRepository underTest;
    @Test
    void itShouldfindCanalNameContaining() {
        //Given
        List<Canal> canaux=Stream.of(new Canal(null,"General","desc Canal ",new Date(),new Date(),null,null),
                  new Canal(null,"Room1","desc Room1 ",new Date(),new Date(),null,null),
                  new Canal(null,"Room2","desc Room2 ",new Date(),new Date(),null,null)
                ).collect(Collectors.toList());
        underTest.saveAll(canaux);
        //When
        String canalAchercher="General";
        List<Canal> results=underTest.findByNameContaining(canalAchercher);
        //Then
        assertTrue(results.stream().allMatch(canal -> canal.getName().equals("General")));
    }
}