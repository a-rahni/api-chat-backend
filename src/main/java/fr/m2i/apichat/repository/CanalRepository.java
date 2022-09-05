package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface CanalRepository extends JpaRepository<Canal,Long> {
    Canal findByName(String name);
}
