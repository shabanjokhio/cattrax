package com.cattrax.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dell User on 4/03/2017.
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
public class PatientDaoServiceImplTest {

    //private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PatientDaoServiceImplTest.class);

    //@Autowired private PatientDaoService patientDaoService;



    /*private PatientDaoServiceImpl patientDaoRepositoryService = new PatientDaoServiceImpl();

    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Before
    public void setup(){
        patientDaoRepositoryService.setPatientRepository(patientRepository);
    }
    @Test
    public void testSaveOrUpdatePatient() {
        logger.info("start testSaveOrUpdatePatient()");
        Patient patient4= new Patient();
        Assert.assertNull(patient4.getId());
        logger.info("Saving patient with ");
        patient4.setPatientFirstNames("John");
        patient4.setLastName("Smith");
        try {
            patient4.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("1/1/1970"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient4.setPatientNHI("JSM1235");
        patient4.setPatientGender(Gender.MALE);
        patient4.setPatientEthnicity(Ethnicity.MAORI);
        patient4.setHealthInsuranceProvider("Some other Insurance");
        patient4.setPatientEmail("jsmith2@mail.com");
        patient4.setPatientPhoneNumber("09-232344");
        patient4.setPatientSmoking(SmokingAnswer.EX_28_DAYS_AGO);
        patient4.setPatientAddress(new Address("2/30 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
       // patient4.setTimeStampPatientRegistered(new Date());
        patientDaoRepositoryService.saveOrUpdate(patient4);
        logger.info("After saving patient into DB...");
        Patient fetchedPatient = patientDaoRepositoryService.getById(patient4.getId());
        Assert.assertNotNull(fetchedPatient.getId());
        logger.info("end testSaveOrUpdatePatient()");
    }
    @Test
    public void testListAllPatients(){

        logger.info("start testListAllPatients()");
        Assert.assertNotNull(patientDaoRepositoryService.listAll());
        logger.info("end testListAllPatients()");
    }
    @Test
    public void testGetPatientById(){
        Patient patient= new Patient();
        logger.info("Saving patient with ");
        patient.setPatientFirstNames("James");
        patient.setLastName("Mck");
        try {
            patient.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/01/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient.setPatientNHI("JMK1234");
        patient.setPatientGender(Gender.MALE);
        patient.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
       // patient2.setEthnicityOtherDescription("Pakistani");
        patient.setHealthInsuranceProvider("Southern cross Insurance");
        patient.setPatientEmail("james@hotmail.com");
        patient.setPatientPhoneNumber("09-5504731sd");
        patient.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient.setPatientSmoking(SmokingAnswer.YES);

        patientDaoRepositoryService.saveOrUpdate(patient);
        logger.info("Saved patient with id- "+patient.getId());

        logger.info("start testGetPatientById()");
        Assert.assertEquals(1, patientDaoRepositoryService.getById(1).getId().intValue());
        logger.info("end testGetPatientById()");
    }
    @Test
    public void testDeletePatient(){
       // initializePatientRecords();
        logger.info("start testDeletePatient()");
        patientDaoRepositoryService.delete(1);
        Assert.assertEquals(null, patientDaoRepositoryService.getById(1));
        logger.info("end testDeletePatient()");
    }

    @Test
    public void testFindByLastName(){
        Patient patient2= new Patient();
        logger.info("Saving patient with ");
        patient2.setPatientFirstNames("Tahira");
        patient2.setLastName("Jokhio");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("10/04/1982"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient2.setPatientNHI("THW371542");
        patient2.setPatientGender(Gender.FEMALE);
        patient2.setPatientEthnicity(Ethnicity.OTHER);
        patient2.setEthnicityOtherDescription("Pakistani");
        patient2.setHealthInsuranceProvider("Southern cross Insurance");
        patient2.setPatientEmail("dr_tahirajokhio@hotmail.com");
        patient2.setPatientPhoneNumber("09-5504731sd");
        patient2.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.YES);
        patientDaoRepositoryService.saveOrUpdate(patient2);
        logger.info("Saved patient with id- "+patient2.getId());

        logger.info("start testFindByLastName()");
        List<Patient> patientsByLastName= patientDaoRepositoryService.findByLastName("Jokhio");
        Assert.assertEquals (1, patientsByLastName.size());

       // Assert.assertEquals("Jokhio", patientDaoRepositoryService.findByLastName("Jokhio"));
        logger.info("end testFindByLastName()");
    }

    @Test
    public void testGetPatientByNHI() {
     logger.info("start testGetPatientByNHI()");

        Patient patient2= new Patient();
        logger.info("Saving patient with ");
        patient2.setPatientFirstNames("John2");
        patient2.setLastName("Smith2");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/01/1972"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient2.setPatientNHI("JSM3214");
        patient2.setPatientGender(Gender.MALE);
        patient2.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        //patient2.setEthnicityOtherDescription("Pakistani");
        patient2.setHealthInsuranceProvider("Southern cross Insurance");
        patient2.setPatientEmail("johnsmith2@hotmail.com");
        patient2.setPatientPhoneNumber("09-550473132");
        patient2.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.YES);
        patientDaoRepositoryService.saveOrUpdate(patient2);
        logger.info("Saved patient with id- "+patient2.getId());
        try {
            Assert.assertEquals("JSM3214",
                    patientDaoRepositoryService.getPatientByNHI("JSM3214").getPatientNHI());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("end testGetPatientByNHI()");
    }
    @Test
    public void testFindByFirstNameLastNameAndDOB() {
        Patient patient2= new Patient();
        logger.info("Saving patient with ");
        patient2.setPatientFirstNames("Muhammad");
        patient2.setLastName("Shaban");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient2.setPatientNHI("MSJ3214");
        patient2.setPatientGender(Gender.MALE);
        patient2.setPatientEthnicity(Ethnicity.OTHER);
        patient2.setEthnicityOtherDescription("Pakistani");
        patient2.setHealthInsuranceProvider("Southern cross Insurance");
        patient2.setPatientEmail("muhammad@hotmail.com");
        patient2.setPatientPhoneNumber("09-5504731sd");
        patient2.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.YES);
       // patient2.setTimeStampPatientRegistered(new Date());
        patientDaoRepositoryService.saveOrUpdate(patient2);
        logger.info("Saved patient with id- "+patient2.getId());

        logger.info("start testFindByFirstNameLastNameAndDOB()");

        try {
            Assert.assertNotNull(
                    patientDaoRepositoryService.
                            findByFirstNameLastNameAndDOB("Muhammad", "Shaban",
                                    new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980")).getLastName());
            Assert.assertEquals(4L,        (long)        patientDaoRepositoryService.
                    findByFirstNameLastNameAndDOB("Muhammad", "Shaban",
                            new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980")).getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("end testFindByFirstNameLastNameAndDOB()");
    }*/
}