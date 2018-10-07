package com.online.seva.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_details")
@Data
public class User {
    /*@Id
    @Column(length = 40)
    @GeneratedValue(generator = "randomId")
    @GenericGenerator(name = "randomId", strategy = "com.online.seva.domain.RandomIdGenerator")
    private String id;*/
    @Id
    @Column(name = "username", unique = true)
    private String username;
    private String name;
    private String email;
    private String password;
    @Transient
    private String passwordConfirm;
    private String phone;
    private boolean active;
    private String centerName;
    private String dob;
    private String aadhar;
    private String state;
    private String district;
    private String city;
    private String pincode;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date createdDate;


    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date modifyDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date lastLogin;
    private String role;

/*    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_students",
            joinColumns = {@JoinColumn(name = "user_name", referencedColumnName = "username")},
            inverseJoinColumns = {@JoinColumn(name = "student_email", referencedColumnName = "email")}
    )
    private List<Student> students;

    public void setStudents(List<Student> students) {
        if (null == this.students)
            this.students = students;
        for (Student student : students) {
            this.students.add(student);
        }

    }

    public void removeStudents(Student student) {
        this.students.remove(student);
    }*/
}