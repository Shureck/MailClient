package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "imap", length = 1000)
    private String imap;
    @Column(name = "port")
    private String port;
    @Column(name = "mail", length = 1000)
    private String mail;
    @Column(name = "password", length = 1000)
    private String password;

    public User(String imap, String port, String mail, String password) {
        this.imap = imap;
        this.port = port;
        this.mail = mail;
        this.password = password;
    }

    @Override
    public String toString() {
        return "mail "+ mail+ " password "+ password;
    }
}
