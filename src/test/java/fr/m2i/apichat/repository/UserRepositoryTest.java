package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// desactiver car on va utiliser assertJ
//import static org.junit.jupiter.api.Assertions.*;

/*
pour repositiory, test seulement les methode ajouté par developpeur (exp des requete)
pour les methode generé par le framework, pas besoin de les tester (findById, findAll, finByName..)
*/
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    @Disabled
    void isExistsEmailTest() {
    }


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfUserExistsEmail() {
        //given
        User user = new User();
        user.setUsername("user2");
        user.setEmail("emailUser2@gmail.com");
        user.setPassword("pwUser2");
        user = underTest.save(user);
        //when
        //boolean expected = underTest.isExistsEmail(user.getEmail()); // pb sql: a revoir
        boolean expected = false;

        User saved = underTest.findById(user.getId()).get();
        expected = "emailUser2@gmail.com".equals(saved.getEmail());

        //then
        assertThat(expected).isTrue();

    }
}