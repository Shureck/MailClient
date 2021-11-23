package com.example.demo.controller;

import com.example.demo.models.Mail;

import javax.mail.*;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class GetMessages {
    private Properties getServerProperties(String protocol, String host,
                                           String port) {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));

        return properties;
    }

    /**
     * Downloads new messages and fetches details for each message.
     *
     * @param protocol
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public ArrayList<Mail> downloadEmails(String protocol, String host, String port,
                                    String userName, String password, Integer page, Integer count) throws MessagingException, IOException {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);
        ArrayList<Mail> mailArrayList = new ArrayList<>();
        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();

            for (int i = messages.length-1-page*count; i > messages.length-(page+1)*count; i--) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(Message.RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(Message.RecipientType.CC));
                String sentDate = msg.getSentDate().toString();

                String contentType = msg.getContentType();

                String bodyContent = "";
                Object content = msg.getContent();

                if(content instanceof String) {
                    System.out.println("Body: " + content);
                    bodyContent = content.toString();
                } else if(content instanceof MimeMultipart) {
                    MimeMultipart multi = (MimeMultipart)content;
                    for(int j = 0; j < multi.getCount(); ++j) {
                        BodyPart bo = multi.getBodyPart(j);
                        //Now that body part could again be a MimeMultipart...
                        bodyContent += bo.getContent();
                        //possibly build a recurion here -> the logic is the same as for mm.getContent() above
                    }
                } else {
                    System.out.println("Some other content: " + content.getClass().getName());
                }

                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
//                System.out.println("\t Message: " + bodyContent);

                mailArrayList.add(new Mail(from,subject,getTextFromMessage(msg),
                        bodyContent, msg.getSentDate(), msg.getReceivedDate()));
            }

            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
        return mailArrayList;
    }

    /**
     * Returns a list of addresses in String format separated by comma
     *
     * @param address an array of Address objects
     * @return a string represents a list of addresses
     */
    private String parseAddresses(Address[] address) {
        String listAddress = "";

        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }

        return listAddress;
    }

    public String getTextFromMessage(Message message) throws IOException, MessagingException {
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
}
