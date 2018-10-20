package com.online.seva.service;

import com.online.seva.domain.Student;
import com.online.seva.domain.StudentImage;
import com.online.seva.domain.User;
import com.online.seva.exception.StorageException;
import com.online.seva.exception.StorageFileNotFoundException;
import com.online.seva.repositories.jpa.JpaUserRepository;
import com.online.seva.repositories.jpa.StudentImageRepository;
import com.online.seva.repositories.jpa.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private StudentImageRepository studentImageRepository;


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
        Optional<Student> byEmail = studentRepository.findByEmail(email);
        try {
            if (byEmail.isPresent()) {
                studentRepository.delete(byEmail.get());
            }
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

    @Override
    public StudentImage storeImage(StudentImage studentImage) {
        MultipartFile multipartFile = studentImage.getFile();
        Optional<StudentImage> byStudent = studentImageRepository.findByStudent(studentImage.getStudent());
        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (multipartFile.isEmpty() || fileName.isEmpty()) {
                //throw new StorageException("Failed to store empty file " + filename);
                log.error("Failed to store empty file::: " + multipartFile.getSize());
                log.error("file::: " + multipartFile);
                log.error("file name::: " + fileName);
                return null;
            }
            if (fileName.contains("..")) {
                throw new StorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            studentImage.setImageName(fileName);
            studentImage.setImageType(multipartFile.getContentType());
            studentImage.setImage(multipartFile.getBytes());
            if (byStudent.isPresent()) {
                log.info("Image already present hence overriding the image...");
                StudentImage existedStd = byStudent.get();
                existedStd.setImageName(fileName);
                existedStd.setImageType(multipartFile.getContentType());
                existedStd.setImage(multipartFile.getBytes());
                return studentImageRepository.save(existedStd);
            }
            return studentImageRepository.save(studentImage);
        } catch (IOException ex) {
            throw new StorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }

    @Override
    public StudentImage fetchImage(Student student) {
        return studentImageRepository.findByStudent(student).get();
    }

    public StudentImage getImageById(String fileId) {
        return studentImageRepository.findById(fileId)
                .orElseThrow(() -> new StorageFileNotFoundException("File not found with id " + fileId));
    }

}
