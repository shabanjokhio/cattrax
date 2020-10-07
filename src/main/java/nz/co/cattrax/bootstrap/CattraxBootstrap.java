package nz.co.cattrax.bootstrap;

import nz.co.cattrax.domain.*;
import nz.co.cattrax.domain.enums.*;
import nz.co.cattrax.services.CattraxReferralService;
import nz.co.cattrax.services.PatientService;
import nz.co.cattrax.services.PracticeService;
import nz.co.cattrax.services.UserService;
import nz.co.cattrax.services.impl.CattraxReferralServiceImpl;
import nz.co.cattrax.services.impl.PatientServiceImpl;
import nz.co.cattrax.services.impl.PracticeServiceImpl;
import nz.co.cattrax.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell User on 22/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@Component
public class CattraxBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private Logger logger = Logger.getLogger(CattraxBootstrap.class);

    @Autowired private PatientService patientService;
    @Autowired private PracticeService practiceService;
    @Autowired private UserService userService;
    @Autowired private CattraxReferralService cattraxReferralService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadPatients();
        loadUsers();
        loadPractices();
        loadReferrals();
    }

    private void loadPractices() {
        logger.info("Creating practice");
        Practice p1= new Practice();
        Address p1Add= new Address();
        p1Add.setAddressLine1("1/24 Pine Street");
        p1Add.setAddressLine2("Mount Eden");
        p1Add.setPostCode(1041);
        p1.setPracticeAddress(p1Add);
        p1.setPracticeName("Spec Savers");
        p1.setPracticeEmail("specsaver@hotmail.com");
        p1.setPracticeFaxNumber("343453");
        p1.setPracticePhoneNumber("09-2234234");
        p1.setPracticeType(PracticeType.OPTOMETRY);

        User newUser1 = new User();
        newUser1.setFirstNames("Muhammad");
        newUser1.setLastName("Shaban");
        newUser1.setUserTitle(Title.MR);
        newUser1.setRoleType(RoleType.REFERRER);
        newUser1.setPhoneNumber("3345345");
        newUser1.setUserEmail("shaban@mail.com");
        newUser1.setUserPassword("my-sec-pw");
        Address newUser1Addr= new Address();
        newUser1Addr.setPostCode(1041);
        newUser1.setUserAddress(newUser1Addr);

        userService.saveOrUpdateUser(newUser1);

        User newUser2 = new User();
        newUser2.setId(1);
        newUser2.setFirstNames("Eddie");
        newUser2.setLastName("Rosser");
        newUser2.setUserTitle(Title.MR);
        newUser2.setRoleType(RoleType.PRACTICE_ADMIN);
        newUser2.setPhoneNumber("65645674");
        newUser2.setUserEmail("eddie@mail.com");
        newUser2.setUserPassword("eddie-pw");

        Address newUser2Addr= new Address();
        newUser2Addr.setPostCode(1042);
        newUser2.setUserAddress(newUser2Addr);

        userService.saveOrUpdateUser(newUser2);

        p1.addUser(newUser1);
        p1.addUser(newUser2);

        logger.info("Saving practice");
        practiceService.saveOrUpdatePractice(p1);
        logger.info("Saved practice with id:\t"+p1.getId());

        logger.info("Creating practice");
        Practice p2= new Practice();
        Address p2Add= new Address();
        p2Add.setAddressLine1("57 Symonds Street");
        p2Add.setAddressLine2("Auckland CBD");
        p1Add.setPostCode(1010);
        p2.setPracticeAddress(p1Add);
        p2.setPracticeName("SSMC");
        p2.setPracticeEmail("ssmc@hotmail.com");
        p2.setPracticeFaxNumber("45465");
        p2.setPracticePhoneNumber("09-6567");
        p2.setPracticeType(PracticeType.GP);

        User newUser3 = new User();
        //newUser1.setId(1);
        newUser3.setFirstNames("Andrew");
        newUser3.setLastName("Grobler");
        newUser3.setUserTitle(Title.DR);
        newUser3.setRoleType(RoleType.REFERRER);
        newUser3.setPhoneNumber("3345345");
        newUser3.setUserEmail("andrewG@mail.com");
        newUser3.setUserPassword("andrw-pw");
        Address newUser3Addr= new Address();
        newUser3Addr.setPostCode(1010);
        newUser3.setUserAddress(newUser3Addr);

        userService.saveOrUpdateUser(newUser3);

        p2.addUser(newUser3);

    }

    private void loadUsers(){
        User james= new User();
        james.setUserTitle(Title.MR);
        james.setFirstNames("James");
        james.setLastName("McK");
        james.setPhoneNumber("09-55023234");
        james.setUserEmail("james@cattrax.com");
        james.setUserPassword("james-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        james.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));

        james.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("123235");
        jamesReg.setRegType(RegistrationType.OPHTHALMOGIST);
        james.setProfessionalRegistration(jamesReg);

        logger.debug("Saving user with name:\t"+ james.getFirstNames());
        userService.saveOrUpdateUser(james);

        User andrew= new User();
        andrew.setUserTitle(Title.DR);
        andrew.setFirstNames("Anrew ");
        andrew.setLastName("Thompson");
        andrew.setPhoneNumber("09-3345345");
        andrew.setUserEmail("andrewt@cattrax.com");
        andrew.setUserPassword("andrew-secret-password");
        Address andrewAddress = new Address();
        andrewAddress.setAddressLine1("100 St. Lukes Road");
        andrewAddress.setPostCode(1042);
        andrew.setUserAddress(andrewAddress);

        List<Practice> andrewWorksIn = new ArrayList<>();
        andrewWorksIn.add(practiceService.getPracticeById(1));

        andrew.setWorksIn(jamesWorksIn);
        Registration andrewReg = new Registration();
        andrewReg.setRegNumber("3434534");
        andrewReg.setRegType(RegistrationType.OPTOMETRIST);
        andrew.setProfessionalRegistration(jamesReg);

        logger.debug("Saving user with name:\t"+ andrew.getFirstNames());
        userService.saveOrUpdateUser(andrew);
    }

    private void loadPatients(){
        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("Muhammad");
        patient1.setLastName("Shaban");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MSH3715");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("Vero Insurance");
        patient1.setPatientEmail("muhammad.shaban@mail.com");
        patient1.setPatientPhoneNumber("09-5504731");

        patient1.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);

        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));

        patientService.registerOrEditPatient(patient1);

        logger.info("Saved patient with id- "+patient1.getId());

        Patient patient2= new Patient();
        logger.info("Saving patient with ");
        patient2.setPatientFirstNames("Tahira");
        patient2.setLastName("Jokhio");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("10/04/1982"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient2.setPatientNHI("THW3715");
        patient2.setPatientGender(Gender.FEMALE);
        patient2.setPatientEthnicity(Ethnicity.OTHER);
        patient2.setEthnicityOtherDescription("Pakistani");
        patient2.setHealthInsuranceProvider("Southern cross Insurance");
        patient2.setPatientEmail("dr_tahirajokhio@hotmail.com");
        patient2.setPatientPhoneNumber("09-5504731");
        patient2.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.YES);
        //patient2.setTimeStampPatientRegistered(new Date());
        patient2.setReferredBy(userService.getUserById(1));
        patient2.setRegisteredBy(userService.getUserById(1));
        patient2.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient2);
        logger.info("Saved patient with id- "+patient2.getId());

        Patient patient3= new Patient();
        logger.info("Saving patient with ");
        patient3.setPatientFirstNames("Kashif");
        patient3.setPatientPhoneNumber("09-5504732");
        patient3.setLastName("Iqbal");
        try {
            patient3.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("10/04/1982"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient3.setPatientNHI("KSI001");
        patient3.setPatientGender(Gender.MALE);
        patient3.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        patient3.setPatientSmoking(SmokingAnswer.EX_28_DAYS_AGO);
        patient3.setHealthInsuranceProvider("Southern cross Insurance");
        patient3.setPatientEmail("kshi@hotmail.com");
        patient3.setPatientAddress(new Address("1/418 Sandrigham Road",
                " Sandrigham",
                "Balmoral","", "",
                1041, "Auckland", "NZ", AddressType.PHYSICAL));
        patient3.setReferredBy(userService.getUserById(1));
        patient3.setRegisteredBy(userService.getUserById(1));
        patient3.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient3);

        logger.info("Saved patient with id- "+patient3.getId());

    }

    private void loadReferrals(){
        CattraxReferral referral_1 = new CattraxReferral();
        logger.info("Saving referral 1");
        try {
            referral_1.setTimeStampCreated(new SimpleDateFormat("dd/mm/yyyy")
                    .parse("10/03/2017"));
        } catch (ParseException pe) {
            logger.error(pe);
        }
        referral_1.setPatientReferred(patientService.getPatientById(1));
        referral_1.setReferredBy(userService.getUserById(1));

        referral_1.setCpacImpactOnLife(new CPACImpactOnLife());
        referral_1.setCpacScoreMeasurement(new CPACScoreMeasurement());
        referral_1.setCpacScoreResult(new CPACScoreResult());

        cattraxReferralService.saveOrEditReferral(referral_1);
        logger.info("Saved referral with id:\t" + referral_1.getId());


        CattraxReferral referral_2 = new CattraxReferral();
        try {
            referral_2.setTimeStampCreated(new SimpleDateFormat("dd/mm/yyyy")
                    .parse("22/03/2017"));
        } catch (ParseException pe) {
            logger.error(pe);
        }
        referral_2.setPatientReferred(patientService.getPatientById(2));
        referral_2.setReferredBy(userService.getUserById(2));

        referral_2.setCpacImpactOnLife(new CPACImpactOnLife());
        referral_2.setCpacScoreMeasurement(new CPACScoreMeasurement());
        referral_2.setCpacScoreResult(new CPACScoreResult());

        cattraxReferralService.saveOrEditReferral(referral_2);
        logger.info("Saved referral with id:\t" + referral_2.getId());
    }

    public void setPatientService(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }
    public void setPracticeService(PracticeServiceImpl practiceService) {
        this.practiceService = practiceService;
    }
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    public void setCattraxReferralService(CattraxReferralServiceImpl cattraxReferralService) {
        this.cattraxReferralService = cattraxReferralService;
    }
}
