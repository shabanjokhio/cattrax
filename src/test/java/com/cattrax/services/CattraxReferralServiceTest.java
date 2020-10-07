package com.cattrax.services;

import com.cattrax.domain.CPACImpactOnLife;
import com.cattrax.domain.CPACScoreMeasurement;
import com.cattrax.domain.CPACScoreResult;
import com.cattrax.domain.CattraxReferral;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ComponentScan(basePackages = {"com.cattrax"})
public class CattraxReferralServiceTest {
    private  org.apache.log4j.Logger logger= Logger.getLogger(CattraxReferralServiceTest.class);

    @Autowired   private PatientService patientService;
    @Autowired   private UserService userService;
    @Autowired   private PracticeService practiceService;
    @Autowired   private CattraxReferralService referralService;

    @Test
    public void testGetAllReferrals(){
        List<CattraxReferral> cattraxReferrals = referralService.listReferrals();
        assert cattraxReferrals != null;

        assert cattraxReferrals.size() ==2;
    }
    @Test
    public void testGetReferralById(){
        CattraxReferral cattraxReferral1= referralService.getReferralById(1);
        assert cattraxReferral1 != null;
        assert cattraxReferral1.getId() == 1;

        CattraxReferral referral2 = referralService.getReferralById(2);
        assert referral2 != null;
        assert referral2.getId() == 2;

    }
    @Test
    public void testSaveOrUpdateReferral(){
        saveOneReferral();
        assert userService.getUserById(1).getFirstNames() == "James";
        System.out.println("OK..!");
    }
    @Test
    public void deleteReferral(){
        referralService.deleteReferral(1);
        assert referralService.getReferralById(1) == null;
    }

    private void saveOneReferral(){

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

        referralService.saveOrEditReferral(referral_1);
        logger.info("Saved referral with id:\t" + referral_1.getId());
    }

}
