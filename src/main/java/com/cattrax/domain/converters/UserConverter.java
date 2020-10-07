package com.cattrax.domain.converters;

import com.cattrax.domain.User;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;


/**
 * Created by Dell User on 13/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@ConfigurationPropertiesBinding
//@javax.persistence.Converter
public class UserConverter implements Converter<String, User> {
    //UserRepository userRepository;
    @Override
    public User convert(String user ) {
    /*     User user_req=null;
        if (StringUtils.isEmpty(user)){
            user_req = new User();
        }
            user_req.setFirstNames("James");
            user_req.setUserId(10);
        return user_req;*/
        return null;
    }
}
