package nz.co.cattrax.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Dell User on 5/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
@Configuration
@EnableJpaRepositories("nz.co.cattrax.repositories")
@ImportResource("classpath:/spring/spring-config.xml")
public class CommonBeanConfig {
/*
    @Override
    public void addFormatters (FormatterRegistry registry){
        registry.addFormatter(AddressFormatter addressFormatter);
    }*/

}
