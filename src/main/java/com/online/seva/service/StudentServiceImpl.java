package com.online.seva.service;

import com.online.seva.domain.Student;
import com.online.seva.domain.User;
import com.online.seva.repositories.jpa.JpaUserRepository;
import com.online.seva.repositories.jpa.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("studentService")
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public void saveStudent(Student student) {
        Student save = studentRepository.save(student);
        log.info("saved Student ::: " + save);
    }

    @Override
    public List<Student> findAll() {
        List<Student> all = studentRepository.findAll();
        if (!all.isEmpty()) {
            for (Student student : all) {
                student.getUser().setPassword(null);
            }
            return all;
        }
        return all;
    }

    @Override
    public List<Student> findAll(String username) {
        User byUsername = jpaUserRepository.findByUsername(username);
        if (byUsername == null) {
            log.error("User does not exists in db");
            return new ArrayList<>();
        }
        return studentRepository.findAllByUser(byUsername).stream().map(student -> {
            student.getUser().setPassword(null);
            if (student.isApproved())
                student.setBonafideUrl("/pdfreport/" + student.getEmail());
            return student;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean isExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public boolean removeStudent(String email) {
        try {
            studentRepository.deleteByEmail(email);
        } catch (Exception e) {
            log.error("Error while remove::" + e);
            return false;
        }
        return true;

    }

    @Override
    public boolean updateEmail(String oldEmail, String newEmail) {
        try {
            studentRepository.updateEmail(oldEmail, newEmail);
        } catch (Exception e) {
            log.error("Error while remove::" + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudent(Student student) {
        Optional<Student> byEmail = studentRepository.findByEmail(student.getEmail());
        if (byEmail.isPresent()) {
            Student save = studentRepository.save(student);
            log.info("updated student :: " + save);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudentApproveStatus(String email) {
        Optional<Student> byEmail = studentRepository.findByEmail(email);
        if (!byEmail.isPresent())
            return false;
        Student student = byEmail.get();
        log.info("fetched student for updating" + student);

        log.info("updating student status :::: current status:: " + student.isApproved());
        if (student.isApproved())
            student.setApproved(false);
        else
            student.setApproved(true);
        Student save = studentRepository.save(student);
        log.info("updated Student status :::: current status:: " + save.isApproved());
        return true;
    }

}
