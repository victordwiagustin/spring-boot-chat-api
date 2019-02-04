package com.ggd.ggdchatapi.dao.repository;

import com.ggd.ggdchatapi.dao.GenericDao;
import com.ggd.ggdchatapi.dao.abst.AbstractJpaDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Primary
public class GenericRepo<T extends Serializable>
        extends AbstractJpaDao<T> implements GenericDao<T> {
}