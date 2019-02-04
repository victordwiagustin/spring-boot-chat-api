package com.ggd.ggdchatapi.dao.custom_repository;

import com.ggd.ggdchatapi.dao.GenericDao;
import com.ggd.ggdchatapi.dao.abst.AbstractJpaDao;

import java.io.Serializable;

//@Repository
public class MessageContentRepo <T extends Serializable>
        extends AbstractJpaDao<T> implements GenericDao<T> {
}
