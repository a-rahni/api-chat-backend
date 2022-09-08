package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  interface CanalRepository extends JpaRepository<Canal,Long> {

    List<Canal> findByNameContaining(String name);

}
