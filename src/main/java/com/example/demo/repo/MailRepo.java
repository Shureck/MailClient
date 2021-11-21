package com.example.demo.repo;

import com.example.demo.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepo extends JpaRepository<Mail, Long> {
    Mail findMailByFromAdress(String str);
}
