package com.online.seva.service;

import com.online.seva.domain.Student;
import com.online.seva.domain.StudentImage;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> findByEmail(String email);

    void saveStudent(Student student);

    List<Student> findAll();

    List<Student> findAll(String student);

    boolean isExists(String email);

    boolean removeStudent(String email);

    boolean updateEmail(String oldEmail, String newEmail);

    boolean updateStudent(Student student);

    boolean updateStudentApproveStatus(String email);

    StudentImage storeImage(StudentImage studentImage);

    StudentImage fetchImage(Student student);

    StudentImage getImageById(String id);
}
