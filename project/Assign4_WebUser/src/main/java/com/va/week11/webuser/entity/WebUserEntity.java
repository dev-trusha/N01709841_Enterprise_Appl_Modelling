package com.va.week11.webuser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "web_user")
public class WebUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "state_of_user")
    private String stateofuser;

    public WebUserEntity() {}

    public WebUserEntity(String username, String password, String stateofuser) {
        this.username = username;
        this.password = password;
        this.stateofuser = stateofuser;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStateofuser() { return stateofuser; }
    public void setStateofuser(String stateofuser) { this.stateofuser = stateofuser; }
}
