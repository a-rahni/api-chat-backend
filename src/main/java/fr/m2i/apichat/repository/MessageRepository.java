package fr.m2i.apichat.repository;

import fr.m2i.apichat.model.Canal;
import fr.m2i.apichat.model.Message;
import fr.m2i.apichat.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long>, PagingAndSortingRepository<Message, Long>, JpaSpecificationExecutor<Message> {

   @Query("select  m from Message m join m.user u join m.canal c where u.username=:username and c.name=:canal")
   List<Message> findAllMessage(@Param("username") String username, @Param("canal") String canal);

   Message getMessageById(Long id);
}
