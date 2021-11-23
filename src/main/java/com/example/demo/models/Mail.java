package com.example.demo.models;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Subject", length = 10000)
    private String subject;
    @Column(name = "bodyDesc", length = 1000000)
    private String bodyDesc;
    @Column(name = "body", length = 1000000)
    private String body;
    @Column(name = "fromAdress", length = 10000)
    private String fromAdress;
    @Column(name = "sendDate")
    private Date sendDate;
    @Column(name = "resDate")
    private Date resDate;
    @Column(name = "unread")
    private boolean unread;
    @Column(name = "favourites")
    private boolean favourites;

    public Mail(String fromAdress, String subject, String bodyDesc, String body, Date sendDate, Date resDate) {
        this.subject = subject;
        this.body = body;
        this.bodyDesc = bodyDesc;
        this.fromAdress = fromAdress;
        this.sendDate = sendDate;
        this.resDate = resDate;
    }

    @Override
    public String toString() {
        return "subject "+ subject+ " body "+ body;
    }
}
