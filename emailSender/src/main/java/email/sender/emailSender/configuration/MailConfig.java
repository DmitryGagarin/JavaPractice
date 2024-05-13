package email.sender.emailSender.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    protected String host;
    @Value("${spring.mail.port}")
    protected int port;
    @Value("${spring.mail.username}")
    protected String username;
    @Value("${spring.mail.password}")
    protected String password;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mail = new JavaMailSenderImpl();

        mail.setHost(host);
        mail.setPort(port);
        mail.setUsername(username);
        mail.setPassword(password);

        Properties props = mail.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");

        return mail;
    }
}
