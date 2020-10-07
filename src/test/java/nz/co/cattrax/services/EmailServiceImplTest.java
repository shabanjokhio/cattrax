package com.cattrax.services;

import com.cattrax.domain.enums.EmailTemplateType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by Dell User on 2/04/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ComponentScan(basePackages = {"com.cattrax"})
public class EmailServiceImplTest {


    @Autowired private EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    @Test
    public void testSendEmail() throws MessagingException {

        String from ="muhammad.shaban@mail.com";
        String to="mshaban@cat-trax.com";
        String subject = "Test Email..";
        String emailBodyTxt="This is just a text email..";

        boolean emailSent = emailService.sendSimpleEmail(from,to, subject, emailBodyTxt);

        assert emailSent == true;
    }

    // public boolean sendTemplateEmail(String from, String to, String subject,
        //                             EmailTemplateType emailType) throws IOException, MessagingException {


    @Test
    public void testSendTemplateEmail() throws IOException, MessagingException {
        String from ="muhammad.shaban@mail.com";
        String to="mshaban@cat-trax.com";
        String subject = "Test Email..";
     //   String emailBodyTxt="This is just a text email..";


        boolean templateEmailSentSuccessfully = emailService.sendTemplateEmail(from,to,subject,EmailTemplateType.WELCOME_EMAIL);
        assert  templateEmailSentSuccessfully == true;

    }
}
