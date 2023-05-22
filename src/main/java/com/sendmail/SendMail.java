package com.sendmail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
        private String to;
        private String from;
        private String pass;
        private static final String host = "smtp.office365.com";
        
        public void setFrom(String from){
            this.from = from;
        }
        
        public void setPass(String pass){
            this.pass = pass;
        }
        
        public void setTo(String to){
            this.to = to;
        }
        
        public void sendEmail(){
            final Session session = Session.getInstance(this.mailProperties(), new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    PasswordAuthentication auth = new PasswordAuthentication(from, pass);
                    return auth;
                }
            });
            try{
                MimeMessage message = new MimeMessage(session);
                message.setFrom(from);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("VENCIMENTO BOLETO X");
                message.setText("SEU BOLETO X VENCE HOJE! LEMBRE-SE DE REALIZAR O PAGAMENTO! ;) ");
                System.out.println("enviando...");
                Transport.send(message);
                System.out.println("Mensagem enviada!");        
            }catch(MessagingException mex){
                mex.printStackTrace();
            }
            
        }
        
        public Properties mailProperties(){
            final Properties  properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587"); //465
            //properties.put("mail.smtp.ssl.enable", true);
            properties.put("mail.smtp.starttls.enable",true);
            //properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.auth", true);
            return properties;
        }               
}