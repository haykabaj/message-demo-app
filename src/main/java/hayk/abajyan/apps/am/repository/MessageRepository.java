package hayk.abajyan.apps.am.repository;

import hayk.abajyan.apps.am.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT * FROM `user-chat`.messages WHERE (sender_id = ?1 and receiver_id = ?2) OR (receiver_id  = ?1 and sender_id = ?2)", nativeQuery = true)
    List<Message> getAll(int senderId, int receiverId);

    @Query(value = "SELECT sender_id FROM messages GROUP BY sender_id ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    int getTheMostUserId();
}
