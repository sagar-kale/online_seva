package com.online.seva.repositories.jpa;

import com.online.seva.domain.Student;
import com.online.seva.domain.StudentImage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!mongodb"})
public interface StudentImageRepository extends JpaRepository<StudentImage, String> {
    StudentImage findByStudent(Student student);
}
