package com.example.demoregister.entity;

import javax.persistence.*;

@Entity
public class Admin {

    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String adminname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Admin() {

    }
    public Admin(Long id,String adminname, String email, String password) {

        this.adminname = adminname;
        this.email = email;
        this.password = password;
        this.id=id;
    }
    public Admin(String adminname, String email, String password) {

        this.adminname = adminname;
        this.email = email;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
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

}
