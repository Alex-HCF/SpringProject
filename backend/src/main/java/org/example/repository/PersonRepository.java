package org.example.repository;

import org.example.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);

    @Query(value = "" +
            "select count(1) from note where owner_id = :perosnId", nativeQuery = true)
    Integer findCountNotesForPerson(@Param("personId") Long personId);

    @Query(value = "" +
            "select count(1) from note " +
            "where status = 'open' and owner_id = :perosnId", nativeQuery = true)
    Integer findCountOpenNotesForPerson(@Param("personId") Long personId);

    @Query(value = "" +
            "select count(1) from person " +
            "where  id >= :startId and id <= :endId", nativeQuery = true)
    List<Person> findPersonByRange(@Param("startId") Long startId, @Param("endId")Long endId);
}
