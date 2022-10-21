package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
    User findByEmail(String email);

    // check if an email exist
    /* probleme avec cette requete
    @Query(""+
           "SELECT CASE WHEN COUNT(u)>0 THEN"+
            "TRUE ELSE FALSE END" +
            "FROM USER u" +
            "WHERE u.email =?1")
    Boolean isExistsEmail(String email);
    */
}
