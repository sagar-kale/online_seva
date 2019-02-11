package com.online.seva.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
public class StudentImage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Basic(fetch = FetchType.EAGER)
    @Lob
    private byte[] image;

    private String imageName;

    private String imageType;

    @Transient
    private MultipartFile file;

    @Transient
    private String email;


    @OneToOne(targetEntity = Student.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "email")
    private Student student;
}
