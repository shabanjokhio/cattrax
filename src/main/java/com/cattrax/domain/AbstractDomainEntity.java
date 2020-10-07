package com.cattrax.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell User on 19/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@MappedSuperclass //this annotation is important otherwise hibernate will not be able to map id or other fields from this super class:
public class AbstractDomainEntity implements DomainEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStampCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStampLastUpdated;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(Date timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public Date getTimeStampLastUpdated() {
        return timeStampLastUpdated;
    }

    public void setTimeStampLastUpdated(Date timeStampLastUpdated) {
        this.timeStampLastUpdated = timeStampLastUpdated;
    }

    @PreUpdate //run this method before update and save, so that we can have correct values ind DB.
    @PrePersist
    public void updateTimeStamps() {
        timeStampLastUpdated = new Date();
        if (timeStampCreated==null) {
            timeStampCreated = new Date();
        }
    }
}
