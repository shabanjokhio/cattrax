package com.cattrax.services;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class CattraxScoringEngineTest {

}
