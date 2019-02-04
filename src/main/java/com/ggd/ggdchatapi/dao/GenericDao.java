package com.ggd.ggdchatapi.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

    void setClazz(Class<T> clazz);

    T findOne(final String id);

    T findOne(String searchBy, String value);

    T findOne(List<String> searchBy, List<Object> value);

    List<T> findAll();

    List<T> findAll(String searchBy, Object value, String orderBy, String orderType, Integer offset, Integer limit);

    List<T> findAll(List<String> searchBy, List<Object> value, String orderBy, String orderType, Integer offset, Integer limit);

    void save(final T entity);

    void  update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId);

}
