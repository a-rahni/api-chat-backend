package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {


    //public Page<Message> findByCanal(Long id, Pageable pageable);
    public List<Message> findByCanal(Long id);

    // JPQL  (pas de select)
    //@Query("FROM Message WHERE canal.getId = ?1")
    //public Page<Message> findByCanalId(Long id, Pageable pageable);

    // SQL native
    //@Query(value = "SELECT * FROM messages WHERE canal_id = :id", nativeQuery = true)
    //public Iterable<Patient> findByCostNative(@Param("id") Long id);
}
