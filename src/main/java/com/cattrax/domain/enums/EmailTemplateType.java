package com.cattrax.domain.enums;

/**
 * Created by Dell User on 18/04/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public enum EmailTemplateType {
    WELCOME_EMAIL("Welcome Email"),
    REFERRAL_EMAIL ("Referral Confirmation or Fax Confirmation"),
    PATIENT_TRANSFER_EMAIL("Transfer of Patient Confirmation Email"),
    CONTACT_REPLY_EMAIL("Contact Cattrax Response Email");

    private String emailTemplateDescription;

    EmailTemplateType(String emailTemplateDescription){
        this.emailTemplateDescription=emailTemplateDescription;
    }

    String getEmailTemplateDescription(){
        return this.emailTemplateDescription;

    }

}
