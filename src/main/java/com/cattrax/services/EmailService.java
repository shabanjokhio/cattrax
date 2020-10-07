package com.cattrax.services;

import com.cattrax.domain.enums.EmailTemplateType;

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
public interface EmailService {

    boolean sendSimpleEmail(String from, String to, String subject, String emailBody) throws MessagingException;

    boolean sendTemplateEmail(String from, String to, String subject, EmailTemplateType emailType) throws IOException, MessagingException;


}
