package org.example.repository;

import org.example.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long id);

    @Query(value = "select * from message m join note n on m.note_id = n.id join " +
            "person p on p.id = m.sender_id or p.id = m.recipient_id " +
            "where n.id = :note_id and (sender_id = :person_id or recipient_id = :person_id ) " +
            "order by m.date", nativeQuery = true)
    List<Message> findMessagesByPersonAndNote(@Param("person_id") Long personId, @Param("note_id") Long noteId);

    @Query(value = "" +
            "select * from message " +
            "where (sender_id = :id1 or sender_id = :id2 ) and (recipient_id = :id1 or recipient_id = :id2)", nativeQuery = true)
    List<Message> findMessageByTwoPerson(@Param("id1") Long id1, @Param("id2") Long id2);

}
