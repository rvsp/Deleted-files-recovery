package com.automail;

import javax.activation.DataHandler;   
import javax.activation.DataSource;   
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;   
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;   
import javax.mail.Session;   
import javax.mail.Transport;   
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;   
import java.io.ByteArrayInputStream;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.OutputStream;   
import java.security.Security;   
import java.util.Date;
import java.util.Properties;   

public class GMailSender extends javax.mail.Authenticator {   
    private String mailhost = "smtp.gmail.com";   
    private String user;   
    private String password;   
    private Session session; 
    private Multipart _multipart;

    static {   
        Security.addProvider(new com.automail.JSSEProvider());   
    }  

    public GMailSender(String user, String password) {   
        this.user = user;   
        this.password = password;   

        Properties props = new Properties();   
        props.setProperty("mail.transport.protocol", "smtp");   
        props.setProperty("mail.host", mailhost);   
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.port", "465");   
        props.put("mail.smtp.socketFactory.port", "465");   
        props.put("mail.smtp.socketFactory.class",   
                "javax.net.ssl.SSLSocketFactory");   
        props.put("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.quitwait", "false");   

        session = Session.getDefaultInstance(props, this);   
    }   

    protected PasswordAuthentication getPasswordAuthentication() {   
        return new PasswordAuthentication(user, password);   
    }   

    public synchronized void sendMail(String subject, String body, String sender, String recipients,String file_name) throws Exception {   
        try{
        	
        	
        	//Properties props = _setProperties();
        	//if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") && !_subject.equals("") && !_body.equals("")) {
        	//Session session = Session.getInstance(props, this);
        	/*MimeMessage msg = new MimeMessage(session);
        	msg.setFrom(new InternetAddress(sender));
        	InternetAddress[] addressTo = new InternetAddress[recipients.length];
        	for (int i = 0; i < recipients.length; i++) {
        	addressTo[i] = new InternetAddress(recipients[i]);
        	}
        	msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);
        	msg.setSubject(subject);
        	msg.setSentDate(new Date());
        	// setup message body
        	BodyPart messageBodyPart = new MimeBodyPart();
        	messageBodyPart.setText(body);
        	_multipart.addBodyPart(messageBodyPart);
        	// Put parts in message
        	msg.setContent(_multipart);
        	// send email
        	Transport.send(msg);*/
        	
        	
        	
        	
        	
        	
        MimeMessage message = new MimeMessage(session);   
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));   
        message.setSender(new InternetAddress(sender));   
        message.setSubject(subject);   
        message.setDataHandler(handler); 
        
        BodyPart messageBodyPart = new MimeBodyPart();
    	DataSource source = new FileDataSource(file_name);
    	messageBodyPart.setDataHandler(new DataHandler(source));
    	messageBodyPart.setFileName(file_name);
    	_multipart.addBodyPart(messageBodyPart);
        message.setContent(_multipart);
        //message.setFileName("/sdcard/cut.jpg");
        
        if (recipients.indexOf(',') > 0)   
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));   
        else  
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));   
        Transport.send(message);   
        }catch(Exception e){
        	
        	int a= 0;

        }
        
    } 
    
    public void addAttachment(String filename) throws Exception {
    	BodyPart messageBodyPart = new MimeBodyPart();
    	DataSource source = new FileDataSource(filename);
    	messageBodyPart.setDataHandler(new DataHandler(source));
    	messageBodyPart.setFileName(filename);
    	_multipart.addBodyPart(messageBodyPart);
    	}
    
   

    public class ByteArrayDataSource implements DataSource {   
        private byte[] data;   
        private String type;   

        public ByteArrayDataSource(byte[] data, String type) {   
            super();   
            this.data = data;   
            this.type = type;   
        }   

        public ByteArrayDataSource(byte[] data) {   
            super();   
            this.data = data;   
        }   

        public void setType(String type) {   
            this.type = type;   
        }   

        public String getContentType() {   
            if (type == null)   
                return "application/octet-stream";   
            else  
                return type;   
        }   

        public InputStream getInputStream() throws IOException {   
            return new ByteArrayInputStream(data);   
        }   

        public String getName() {   
            return "ByteArrayDataSource";   
        }   

        public OutputStream getOutputStream() throws IOException {   
            throw new IOException("Not Supported");   
        }   
    }   
}  

