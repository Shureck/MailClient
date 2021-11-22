package com.example.demo.controller;

import com.example.demo.models.Mail;
import com.example.demo.repo.MailRepo;
import org.jsoup.Jsoup;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

@Controller
public class getMail {

    private String getTextFromMessage(Message message) throws IOException, MessagingException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws IOException, MessagingException {

        int count = mimeMultipart.getCount();
        if (count == 0)
            throw new MessagingException("Multipart with no body parts not supported.");
        boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
        if (multipartAlt)
            // alternatives appear in an order of increasing
            // faithfulness to the original content. Customize as req'd.
            return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
        String result = "";
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            result += getTextFromBodyPart(bodyPart);
        }
        return result;
    }

    private String getTextFromBodyPart(
            BodyPart bodyPart) throws IOException, MessagingException {

        String result = "";
        if (bodyPart.isMimeType("text/plain")) {
            result = (String) bodyPart.getContent();
        } else if (bodyPart.isMimeType("text/html")) {
            String html = (String) bodyPart.getContent();
            result = org.jsoup.Jsoup.parse(html).text();
        } else if (bodyPart.getContent() instanceof MimeMultipart){
            result = getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }
        return result;
    }

    @Autowired
    MailRepo mailRepo;
    @Autowired
    private JavaMailSender emailSender;

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
        System.out.println("Body23: " + getTextFromMessage(messages[page]));
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

    @GetMapping("/test")
    public String getMsg() throws MessagingException, IOException {
        final Properties props = new Properties();

        mailRepo.deleteAll();

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

        // 4. Get the POP3 store provider and connect to the store.
        Store store = session.getStore("pop3");
        store.connect("pop.gmail.com","sashalev200149@gmail.com", "txrwzaqkdebrjizs");

        // 5. Get folder and open the INBOX folder in the store.
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // 6. Retrieve the messages from the folder.
        Message[] messages = inbox.getMessages();
        System.out.println(messages.length);
        for(int i=messages.length-1; i>0; i--){
            System.out.println(messages[i].getSubject());

//            messages[i].writeTo();

            String mimeType = messages[i].getContentType();
            System.out.println("Message is a " + mimeType);
            Object content = messages[i].getContent();
            String bodyContent = "";
            if(content instanceof String) {
                System.out.println("Body: " + content);
                bodyContent = content.toString();
            } else if(content instanceof MimeMultipart) {
                MimeMultipart multi = (MimeMultipart)content;
                System.out.println("We have a "+ multi.getContentType());
                for(int j = 0; j < multi.getCount(); ++j) {
                    BodyPart bo = multi.getBodyPart(j);
                    System.out.println("Content "+j+" is a " + bo.getContentType());
                    //Now that body part could again be a MimeMultipart...
                    bodyContent += bo.getContent();
                    System.out.println("Loooool "+bodyContent);
                    //possibly build a recurion here -> the logic is the same as for mm.getContent() above
                }
            } else {
                System.out.println("Some other content: " + content.getClass().getName());
            }

            mailRepo.save(new Mail(messages[i].getFrom()[0].toString(),messages[i].getSubject(),getTextFromMessage(messages[i]),
                                bodyContent,messages[i].getSentDate(), messages[i].getReceivedDate()));

        }

        // 7. Close folder and close store.
        inbox.close(false);
        store.close();
        return "DFD";
    }
}
