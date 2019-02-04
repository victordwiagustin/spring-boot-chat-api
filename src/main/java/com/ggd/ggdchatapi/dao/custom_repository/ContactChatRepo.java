package com.ggd.ggdchatapi.dao.custom_repository;

import com.ggd.ggdchatapi.dao.GenericDao;
import com.ggd.ggdchatapi.dao.abst.AbstractJpaDao;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ContactChatRepo<T extends Serializable>
        extends AbstractJpaDao<T> implements GenericDao<T> {

    @Override
    public List<T> findAll(String searchBy, Object value, String orderBy, String orderType, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder("select x from " + super.getClazz().getName() + " x where 1=1 ")
                .append(" and x." + searchBy + " = :p ");

        if (orderBy != null && !orderBy.equals("")) {
            sb.append(" order by x." + orderBy).append(" ")
                    .append(orderType == null || orderType.equals("") ||
                            !(orderType.trim().equalsIgnoreCase("ASC") || orderType.trim().equalsIgnoreCase("DESC"))
                            ? "ASC " : orderType);
        }
        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("p", value);

        if (offset != null && offset >= 0) {
            query.setFirstResult(offset);
        }

        if (limit != null && limit > 0) {
            query.setMaxResults(limit);
        }

        System.out.println("findAll\n " + sb.toString());

        return query.getResultList();
    }
}
