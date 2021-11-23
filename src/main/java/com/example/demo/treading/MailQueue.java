package com.example.demo.treading;

import com.example.demo.controller.GetMessages;
import com.example.demo.repo.MailRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class MailQueue extends Thread    {// Наша очередь из сотрудников, наследник класса Thread
    String protocol;
    String host;
    String port;
    String email;
    String password;
    GetMessages getMessages = new GetMessages();

    @Autowired
    MailRepo mailRepo;

    public MailQueue(String protocol, String host, String port, String email, String password) {// Конструктор, аргумент- массив имен сотрудников
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.email = email;
        this.password = password;
    }

    @SneakyThrows
    @Override
    public void run() { // Этот метод будет вызван при старте потока
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        for (int i=1;i<15;i++){
            System.out.println("Index "+i);
            mailRepo.saveAll(getMessages.downloadEmails(protocol, host, port, email, password, i, 10));
        }
    }
}