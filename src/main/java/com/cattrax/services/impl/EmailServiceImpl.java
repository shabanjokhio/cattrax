package com.cattrax.services.impl;

import com.cattrax.domain.enums.EmailTemplateType;
import com.cattrax.services.EmailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Dell User on 2/04/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
@Service
public class EmailServiceImpl implements EmailService{

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.host}")
    private String host;

    @Override
    public boolean sendSimpleEmail(String from, String to, String subject,
                                   String emailBody) throws MessagingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setText(emailBody);
        sender.send(message);
        return true;
    }

    @Override
    public boolean sendTemplateEmail(String from, String to, String subject,
                                     EmailTemplateType emailType) throws IOException, MessagingException {

        Map<String, Object> model = new HashMap<String, Object>();

		//model.put("body", );
        //VelocityEngine velocityEngine= velocityEngineFactory.createVelocityEngine();

        VelocityEngineFactory factory = new VelocityEngineFactory();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        factory.setVelocityProperties(props);
        VelocityEngine velocityEngine = factory.createVelocityEngine();

        String emailTemplate=null;

        switch (emailType){
            case WELCOME_EMAIL:
                emailTemplate="welcomeEmail.vm";
                model.put("firstName", "Muhammad");
                model.put("lastName", " Shaban");
                break;
            case CONTACT_REPLY_EMAIL:
                emailTemplate="contact_reply_email.vm";
                break;
            case PATIENT_TRANSFER_EMAIL:
                emailTemplate = "patient_transfer_email.vm";
                break;
            case REFERRAL_EMAIL:
                emailTemplate="referral_email";
                break;
            default:
                //throw exception.
        }
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate,
                "UTF-8", model);

        System.out.println(text);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        MimeMessage message = sender.createMimeMessage();
        sender.setHost(host);
        sender.setPort(port);

        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setText(text);
        sender.send(message);

        return true;
    }
}
