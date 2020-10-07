package com.cattrax.services.impl;

import com.cattrax.domain.CPACScoreMeasurement;
import com.cattrax.domain.CattraxReferral;
import com.cattrax.domain.enums.*;
import com.cattrax.services.CattraxScoringEngine;
import org.springframework.stereotype.Service;

/**
 * Created by Dell User on 22/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
@Service
public class CattraxScoringEngineImpl implements CattraxScoringEngine{
    /**
     * @param referral
     * @return: CPAC score that will tell us the eligibility for the public funding
     */
    public double calculateCPACScore(CattraxReferral referral) {
        //TODO: Implement this method:
        double cpacScore = 0;
        CPACScoreMeasurement cpac = referral.getCpacScoreMeasurement();
        if (cpac.getLensStatus_OD().equals(LensStatus.PHAKIC) ||  cpac.getLensStatus_OD().equals(LensStatus.APHAKIC)) {
            //Only iff this condition is true, then CPAC or other scores are calculated
            if (referral.getCpacScoreResult().getEyeReferred() == Eye.OD) {
                double logmar_OD_actuity = (Math.log(6 / cpac.getBcvaLine_OD().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OD());
                double logmar_OD = 0;
                if (logmar_OD_actuity == 0) {
                    logmar_OD = 0;
                } else if (logmar_OD_actuity > 0 && logmar_OD_actuity <= 0.17609125905568) {
                    logmar_OD = 1.32;
                } else if (logmar_OD_actuity > 0.17609125905568 && logmar_OD_actuity <= 0.30102999566398) {
                    logmar_OD = 7.5;
                } else if (logmar_OD_actuity > 0.30102999566398 && logmar_OD_actuity <= 0.39794000867204) {
                    logmar_OD = 7.68;
                } else if (logmar_OD_actuity > 0.39794000867204 && logmar_OD_actuity <= 0.47712125471966) {
                    logmar_OD = 8.30;
                } else if (logmar_OD_actuity > 0.47712125471966 && logmar_OD_actuity <= 0.60205999132796) {
                    logmar_OD = 11.79;
                } else if (logmar_OD_actuity > 0.60205999132796 && logmar_OD_actuity <= 0.77815125038364) {
                    logmar_OD = 14.43;
                } else if (logmar_OD_actuity > 0.77815125038364 && logmar_OD_actuity <= 1) {
                    logmar_OD = 16.97;
                } else if (logmar_OD_actuity > 1 && logmar_OD_actuity <= 3) {
                    logmar_OD = 18.19;
                }

                double logmar_OU_actuity = (Math.log(6 / cpac.getBcvaLine_OU().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OU());
                double logmar_OU = 0;
                if (logmar_OU_actuity == 0) {
                    logmar_OU = 0;
                } else if (logmar_OU_actuity > 0 && logmar_OU_actuity <= 0.17609125905568) {
                    logmar_OU = 6.35;
                } else if (logmar_OU_actuity > 0.17609125905568 && logmar_OU_actuity <= 0.30102999566398) {
                    logmar_OU = 11.74;
                } else if (logmar_OU_actuity > 0.30102999566398 && logmar_OU_actuity <= 0.39794000867204) {
                    logmar_OU = 13.46;
                } else if (logmar_OU_actuity > 0.39794000867204 && logmar_OU_actuity <= 0.47712125471966) {
                    logmar_OU = 15.33;
                } else if (logmar_OU_actuity > 0.47712125471966 && logmar_OU_actuity <= 0.60205999132796) {
                    logmar_OU = 19.89;
                } else if (logmar_OU_actuity > 0.60205999132796 && logmar_OU_actuity <= 0.77815125038364) {
                    logmar_OU = 25.87;
                } else if (logmar_OU_actuity > 0.77815125038364 && logmar_OU_actuity <= 1) {
                    logmar_OU = 35.36;
                } else if (logmar_OU_actuity > 1 && logmar_OU_actuity <= 3) {
                    logmar_OU = 38.95;
                }

                double impactOfLife = 0;
                impactOfLife += referral.getCpacImpactOnLife().getQ1_socialInteraction().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ2_personalInteraction().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ3_fulfillingResponsibilities().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ4_personalCare().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ5_personalSafety().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ6_leisureActivities().getDifficultyScore();

                double PSC_OD = 0;
                if (cpac.getPsc_OD() == PostSubcapsular.ABSENT) {
                    PSC_OD = 0;
                } else {
                    PSC_OD = 6.38;
                }

                double visualPotential_OD = 0;
                if (cpac.getVisualPotential_OD() == VisualPotential._BETTER_THAN_6By12) {
                    visualPotential_OD = 23.40;
                } else if (cpac.getVisualPotential_OD() == VisualPotential._BETTER_THAN_6By12_TO_6By36) {
                    visualPotential_OD = 19.89;
                } else if (cpac.getVisualPotential_OD() == VisualPotential._WORSE_6By36) {
                    visualPotential_OD = 0;
                }

                cpacScore = impactOfLife + logmar_OD + logmar_OU + PSC_OD + visualPotential_OD;

            } else if (referral.getCpacScoreResult().getEyeReferred() == Eye.OS) {
                double logmar_OS_actuity = (Math.log(6 / cpac.getBcvaLine_OS().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OS());

                double logmar_OS = 0;
                if (logmar_OS_actuity == 0) {
                    logmar_OS = 0;
                } else if (logmar_OS_actuity > 0 && logmar_OS_actuity <= 0.17609125905568) {
                    logmar_OS = 1.32;
                } else if (logmar_OS_actuity > 0.17609125905568 && logmar_OS_actuity <= 0.30102999566398) {
                    logmar_OS = 7.5;
                } else if (logmar_OS_actuity > 0.30102999566398 && logmar_OS_actuity <= 0.39794000867204) {
                    logmar_OS = 7.68;
                } else if (logmar_OS_actuity > 0.39794000867204 && logmar_OS_actuity <= 0.47712125471966) {
                    logmar_OS = 8.30;
                } else if (logmar_OS_actuity > 0.47712125471966 && logmar_OS_actuity <= 0.60205999132796) {
                    logmar_OS = 11.79;
                } else if (logmar_OS_actuity > 0.60205999132796 && logmar_OS_actuity <= 0.77815125038364) {
                    logmar_OS = 14.43;
                } else if (logmar_OS_actuity > 0.77815125038364 && logmar_OS_actuity <= 1) {
                    logmar_OS = 16.97;
                } else if (logmar_OS_actuity > 1 && logmar_OS_actuity <= 3) {
                    logmar_OS = 18.19;
                }

                double logmar_OU_actuity = (Math.log(6 / cpac.getBcvaLine_OU().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OU());
                double logmar_OU = 0;
                if (logmar_OU_actuity == 0) {
                    logmar_OU = 0;
                } else if (logmar_OU_actuity > 0 && logmar_OU_actuity <= 0.17609125905568) {
                    logmar_OU = 6.35;
                } else if (logmar_OU_actuity > 0.17609125905568 && logmar_OU_actuity <= 0.30102999566398) {
                    logmar_OU = 11.74;
                } else if (logmar_OU_actuity > 0.30102999566398 && logmar_OU_actuity <= 0.39794000867204) {
                    logmar_OU = 13.46;
                } else if (logmar_OU_actuity > 0.39794000867204 && logmar_OU_actuity <= 0.47712125471966) {
                    logmar_OU = 15.33;
                } else if (logmar_OU_actuity > 0.47712125471966 && logmar_OU_actuity <= 0.60205999132796) {
                    logmar_OU = 19.89;
                } else if (logmar_OU_actuity > 0.60205999132796 && logmar_OU_actuity <= 0.77815125038364) {
                    logmar_OU = 25.87;
                } else if (logmar_OU_actuity > 0.77815125038364 && logmar_OU_actuity <= 1) {
                    logmar_OU = 35.36;
                } else if (logmar_OU_actuity > 1 && logmar_OU_actuity <= 3) {
                    logmar_OU = 38.95;
                }

                double impactOfLife = 0;
                impactOfLife += referral.getCpacImpactOnLife().getQ1_socialInteraction().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ2_personalInteraction().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ3_fulfillingResponsibilities().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ4_personalCare().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ5_personalSafety().getDifficultyScore();
                impactOfLife += referral.getCpacImpactOnLife().getQ6_leisureActivities().getDifficultyScore();

                double PSC_OS = 0;
                if (cpac.getPsc_OS() == PostSubcapsular.ABSENT) {
                    PSC_OS = 0;
                } else {
                    PSC_OS = 6.38;
                }

                double visualPotential_OS = 0;
                if (cpac.getVisualPotential_OS() == VisualPotential._BETTER_THAN_6By12) {
                    visualPotential_OS = 23.40;
                } else if (cpac.getVisualPotential_OS() == VisualPotential._BETTER_THAN_6By12_TO_6By36) {
                    visualPotential_OS = 19.89;
                } else if (cpac.getVisualPotential_OS() == VisualPotential._WORSE_6By36) {
                    visualPotential_OS = 0;
                }

                cpacScore = impactOfLife + logmar_OS + logmar_OU + PSC_OS + visualPotential_OS;

            }
        }
        return cpacScore;
    }

    /**
     * @param referral
     * @param threshold
     * @return
     */
    public boolean isEligibleForPublicFundedCataractSurgery(CattraxReferral referral,
                                                            double threshold) {
        return this.calculateCPACScore(referral) >= threshold;
    }
    /**
     * @param referral
     * @return true f the patient is the
     */
    public boolean isEligibleForSouthernCrossFunding(CattraxReferral referral) {
        boolean condition1 = false;
        boolean condition2 = false;
        boolean condition3 = false;
        CPACScoreMeasurement cpac = referral.getCpacScoreMeasurement();
        boolean condition4 = ((cpac.getSphere_OD() + 0.5 * (cpac.getCyl_OD())) - (cpac.getSphere_OS() + 0.5 * (cpac.getCyl_OS()))) >= 2;
        if (referral.getCpacScoreResult().getEyeReferred() == Eye.OD) {
            double logmar_OD = (Math.log(6 / cpac.getBcvaLine_OD().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OD());
            condition1 = cpac.getPsc_OD() != PostSubcapsular.ABSENT || cpac.getNucC_OD() != NuclearColour.ABSENT || cpac.getNucO_OD() != NuclearOpalescence.ABSENT || cpac.getCort_OD() != Cortical.ABSENT && logmar_OD >= 0.17609125905568;
            condition2 = cpac.getPsc_OD() != PostSubcapsular.ABSENT || cpac.getCort_OD() != Cortical.ABSENT && cpac.isGlare_OD();
            condition3 = cpac.getNucC_OD() != NuclearColour.ABSENT || cpac.getNucO_OD() != NuclearOpalescence.ABSENT && cpac.isMyopicShift_OD();

        } else if (referral.getCpacScoreResult().getEyeReferred() == Eye.OS) {
            double logmar_OS = (Math.log(6 / cpac.getBcvaLine_OS().getBcvaValue()) * (-1)) - (0.02 * cpac.getBcvaLetter_OS());

            condition1 = cpac.getPsc_OS() != PostSubcapsular.ABSENT || cpac.getNucC_OS() != NuclearColour.ABSENT || cpac.getNucO_OS() != NuclearOpalescence.ABSENT || cpac.getCort_OS() != Cortical.ABSENT && logmar_OS >= 0.17609125905568;
            condition2 = cpac.getPsc_OS() != PostSubcapsular.ABSENT || cpac.getCort_OS() != Cortical.ABSENT && cpac.isGlare_OS();
            condition3 = cpac.getNucC_OS() != NuclearColour.ABSENT || cpac.getNucO_OS() != NuclearOpalescence.ABSENT && cpac.isMyopicShift_OS();
        }
        return condition1 || condition2 || condition3 || condition4;
    }
    public boolean isEligibleForOtherInsuranceFunding(CattraxReferral referral) {
        CPACScoreMeasurement cpac = referral.getCpacScoreMeasurement();
        boolean condition = false;
        if (referral.getCpacScoreResult().getEyeReferred() == Eye.OS) {
            condition = cpac.getPsc_OS() != PostSubcapsular.ABSENT || cpac.getNucC_OS() != NuclearColour.ABSENT || cpac.getNucO_OS() != NuclearOpalescence.ABSENT || cpac.getCort_OS() != Cortical.ABSENT;
        } else if (referral.getCpacScoreResult().getEyeReferred() == Eye.OD) {
            condition = cpac.getPsc_OD() != PostSubcapsular.ABSENT || cpac.getNucC_OD() != NuclearColour.ABSENT || cpac.getNucO_OD() != NuclearOpalescence.ABSENT || cpac.getCort_OD() != Cortical.ABSENT;
        }
        return condition;
    }

}