package com.joalvarez.wharehousecontrol.general.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<MAP> {

    protected MAP mapper;

    @Autowired
    public BaseService(MAP mapper) {
        this.mapper = mapper;
    }
}
