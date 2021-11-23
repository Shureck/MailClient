package com.example.demo.controller;

import com.example.demo.models.Mail;
import com.example.demo.repo.MailRepo;
import com.example.demo.treading.MailQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

@Controller
public class getMail {

    @Autowired
    MailRepo mailRepo;
    @Autowired
    private JavaMailSender emailSender;

    private GetMessages getMessages = new GetMessages();

    private String userName = "sashalev200149@gmail.com";
    private String password = "txrwzaqkdebrjizs";

    private String protocol = "imap";
    private String host = "imap.yandex.ru";
    private String port = "993";

    @GetMapping("/")
    public String mainMail(Model model, Integer page) throws MessagingException, IOException {
        System.out.println(page);
        Pageable pageable = PageRequest.of(0, 9);
        if(page != null) {
            pageable = PageRequest.of(page, 9);
        }
        ArrayList<Mail> mailArrayList = new ArrayList<Mail>(mailRepo.findAll(pageable).getContent());
        System.out.println(mailArrayList.toString());

        model.addAttribute("MainMail", mailArrayList);

        return "MainMail";
    }

    @PostMapping("/sendMail")
    public String sendMail(Model model, String form, String to, String subject, String message){

        System.out.println(form+" " + to + " "+subject+" "+message);

        SimpleMailMessage messaged = new SimpleMailMessage();
        messaged.setFrom("Sashalev200149@gmail.com");
        messaged.setTo(to);
        messaged.setSubject(subject);
        messaged.setText(message);
        emailSender.send(messaged);

        return "MainMail";
    }

    @GetMapping("/createMail")
    public String createMail(Model model) {
        return "NewMail";
    }

    @GetMapping("/readMail")
    public String readMail(Model model, Long page) throws IOException, MessagingException {

        Mail mail = mailRepo.findMailById(page);

        model.addAttribute("ReadMail",mail);

        return "ReadMail";
    }

    @GetMapping("/read")
    public String read(Model model, int page) throws IOException, MessagingException {
//        Runtime rt = Runtime.getRuntime();
//        String sttr = "";
//        String processString = "python3 src/main/java/com/example/demo/controller/AdaMqtt.py";
//        System.out.println(processString);
//
//        ProcessBuilder pb = new ProcessBuilder("python3","src/main/java/com/example/demo/controller/AdaMqtt.py", "\""+sttr+"\"");
//        Process p = pb.start();
//        System.out.println(new String(p.getInputStream().readAllBytes(), Charset.forName("Cp1251") ));
//
        final Properties props = new Properties();
        System.out.println(page);

        //XTrustProvider.install();

        // System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", true);

        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty( "mail.pop3.host", "pop.gmail.com" );
        props.setProperty( "mail.pop3.user", "sashalev200149@gmail.com");
        props.setProperty( "mail.pop3.password", "txrwzaqkdebrjizs");
        props.setProperty( "mail.pop3.ssl.enable", "true");
        props.setProperty( "mail.pop3.port", "995" );
        props.setProperty( "mail.pop3.auth", "true" );
        props.setProperty("mail.pop3.starttls.enable", "false");
       /* props.setProperty( "mail.pop3.starttls.enable", "true" );
        props.setProperty( "mail.pop3.starttls.required", "true" );*/

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sashalev200149@gmail.com", "txrwzaqkdebrjizs");
            }
        };

        Session session  = Session.getInstance(props);
        session.setDebug(true);

        session = Session.getDefaultInstance(props, auth);

        Store store = session.getStore("pop3");
        store.connect("pop.gmail.com","sashalev200149@gmail.com", "txrwzaqkdebrjizs");

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        System.out.println(messages.length);
        Object content = new Object();
        System.out.println(messages[page].getSubject());

        String mimeType = messages[page].getContentType();
        System.out.println("Message is a " + mimeType);
        content = messages[page].getContent();
        System.out.println("Body21: " + content.getClass().getName());
        System.out.println("Body22: " + content);
        System.out.println("Body23: " + getMessages.getTextFromMessage(messages[page]));
        System.out.println("Body24: " + messages[page].getFlags());
        if(content instanceof String) {
            System.out.println("Body: " + content.getClass().getName());
            System.out.println("Body: " + content);
        } else if(content instanceof MimeMultipart) {
            MimeMultipart multi = (MimeMultipart)content;
            System.out.println("We have a "+ multi.getContentType());
            String bodyContent = "";
            for(int j = 0; j < multi.getCount(); ++j) {
                BodyPart bo = multi.getBodyPart(j);
                System.out.println("Content "+j+" is a " + bo.getContentType());
                bodyContent += bo.getContent();
                System.out.println("Loooool "+bodyContent);
            }
            content = bodyContent;
        } else {
            System.out.println("Some other content: " + content.getClass().getName());
        }

        inbox.close(false);
        store.close();

        model.addAttribute("MainModel",content.toString());

        return "ReadMail";
    }

    @GetMapping("/login")
    public String getMsg(Model model) throws MessagingException, IOException {
        final Properties props = new Properties();

        return "create-project";
    }

    @GetMapping("/download")
    public String download(Model model) throws MessagingException, IOException {
        final Properties props = new Properties();

        mailRepo.deleteAll();

        for (int i=0;i<15;i++){
            System.out.println("Index "+i);
            mailRepo.saveAll(getMessages.downloadEmails(protocol, host, port, userName, password, i, 10));
        }

        return "redirect:"+"/";
    }

    @PostMapping("/test")
    public String login(Model model, String email, String password) throws MessagingException, IOException {
        final Properties props = new Properties();
        mailRepo.deleteAll();

        //XTrustProvider.install();

        // System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", true);


//        String userName = "sashalev200149@gmail.com";
//        String password = "txrwzaqkdebrjizs";

        this.userName = email;
        this.password = password;

        mailRepo.saveAll(getMessages.downloadEmails(protocol, host, port, email, password, 0, 10));

        return "redirect:"+"/";
    }
}
