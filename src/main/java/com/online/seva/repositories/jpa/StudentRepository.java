package com.online.seva.repositories.jpa;

import com.online.seva.domain.Student;
import com.online.seva.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile({"!mongodb"})
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByEmail(String email);

    @Modifying
    @Query("update Student s set s.email = :new_email where s.email = :email")
    void updateEmail(@Param("email") String email, @Param("new_email") String new_email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

    //@Query("select s from Student s JOIN fetch s.user u where u.username=:username")
    List<Student> findAllByUser(User user);
}
