package com.cattrax.domain.converters;

import com.cattrax.domain.Practice;
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
public class PracticeConverter implements Converter<String, Practice> {
    @Override
    public Practice convert(String s) {
        /*Practice p = null;
        if(StringUtils.isEmpty(s))
            p= new Practice();
        p.setPracticeId(1);
        p.setPracticeEmail("info@ssmc.co.nz");
        p.setPracticeName("SSMC");
        return p;*/
        return null;
    }
}
