package com.ggd.ggdchatapi.dao.abst;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.stream.IntStream;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T findOne(String id) {
        return entityManager.find(clazz, id);
    }

    public T findOne(String searchBy, String value) {
        Query query = entityManager.createQuery("select x from " + clazz.getName() + " x where x." + searchBy + " = :p");
        query.setParameter("p", value);
        return (T) query.getSingleResult();
    }

    public T findOne(List<String> searchBy, List<Object> value) throws NoResultException {
        StringBuilder sb = new StringBuilder("select x from " + clazz.getName() + " x where 1=1 ");
        IntStream.range(0, searchBy.size()).forEach(i -> sb.append(" and x." + searchBy.get(i) + " = :p" + i));
        Query query = entityManager.createQuery(sb.toString());
        IntStream.range(0, searchBy.size()).forEach(i -> query.setParameter("p" + i, value.get(i)));
        System.out.println(sb.toString());
        return (T) query.getSingleResult();
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public List<T> findAll(String searchBy, Object value, String orderBy, String orderType, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder("select x from " + clazz.getName() + " x where 1=1 ")
                .append(" and x." + searchBy + " like '%" + value + "%' ");

        if (orderBy != null && !orderBy.equals("")) {
            sb.append(" order by x." + orderBy).append(" ")
                    .append(orderType == null || orderType.equals("") ||
                            !(orderType.trim().equalsIgnoreCase("ASC") || orderType.trim().equalsIgnoreCase("DESC"))
                            ? "ASC " : orderType);
        }
        Query query = entityManager.createQuery(sb.toString());

        if (offset != null && offset >= 0) {
            query.setFirstResult(offset);
        }

        if (limit != null && limit > 0) {
            query.setMaxResults(limit);
        }

        System.out.println(sb.toString());
        return query.getResultList();
    }

    public List<T> findAll(List<String> searchBy, List<Object> value, String orderBy, String orderType, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder("select x from " + clazz.getName() + " x where 1=1 ");

        IntStream.range(0, searchBy.size()).forEach(i -> sb.append(" and x." + searchBy.get(i) + " like %" + value.get(i) + "% "));

        if (orderBy != null && !orderBy.equals("")) {
            sb.append(" order by x." + orderBy).append(" ")
                    .append(orderType == null || orderType.equals("") ||
                            !(orderType.trim().equalsIgnoreCase("ASC") || orderType.trim().equalsIgnoreCase("DESC"))
                            ? "ASC " : orderType);
        }
        Query query = entityManager.createQuery(sb.toString());

        if (offset != null && offset >= 0) {
            query.setFirstResult(offset);
        }

        if (limit != null && limit > 0) {
            query.setMaxResults(limit);
        }

        System.out.println(sb.toString());
        return query.getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(String entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
