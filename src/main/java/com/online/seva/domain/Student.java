package com.online.seva.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    private String email;

    @Transient
    private String username;

    private String name;
    private String phone;
    private String dob;
    private String aadhar;
    private String state;
    private String district;
    private String city;
    private String pincode;
    private String courseName;
    private String admitCardUrl;
    private String bonafideUrl;
    private boolean approved;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
    private User user;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date modifyDate;
}