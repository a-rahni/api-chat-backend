package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  interface CanalRepository extends JpaRepository<Canal,Long> {
    List<Canal> findByNameContaining(String name);
@Query("select case when count(c)>0 then true else false end from Canal c where c.name=?1")
    Boolean selectExistsCanal(String name);
}
