package com.online.seva.domain;

import javax.persistence.*;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Entity(name = "user_details")
public class User  implements HttpSessionBindingListener {
    /*@Id
    @Column(length = 40)
    @GeneratedValue(generator = "randomId")
    @GenericGenerator(name = "randomId", strategy = "com.online.seva.domain.RandomIdGenerator")
    private String id;*/
    @Id
    @Column(name = "username", unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Transient
    private String passwordConfirm;
    private String phone;
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_name", referencedColumnName = "username")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;
/*
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
