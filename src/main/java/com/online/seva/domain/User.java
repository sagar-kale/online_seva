package com.online.seva.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;

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

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp modifyDate;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp lastLogin;
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