package com.cattrax.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by shabanm on 5/04/2017.
 */

@EnableAutoConfiguration
@EntityScan(basePackages = {"com.cattrax"})
@EnableTransactionManagement
//@Configuration
//@EnableJpaRepositories("com.cattrax.repositories")
public class MailConfig {
    //@Autowired
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
}
