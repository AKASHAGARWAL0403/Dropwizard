package com.mt.DB;
/**
 * Created by bing.du on 2/24/14.
 */

import com.google.common.base.Optional;
import com.mt.Core.Store;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;
import org.hibernate.SessionFactory;

import javax.ws.rs.GET;
import java.util.List;

public class StoreDao extends AbstractDAO<Store> {
    public StoreDao(SessionFactory factory) {
        super(factory);
    }

    public Store findById(int id) {
        return get(id);
    }

    public Store create(Store store) {
        return persist(store);
    }

    public void delete(int id)
    {
        currentSession().delete(get(id));
    }

    public void edit(Store store)
    {
        persist(store);
    }

    public List<Store> findAll() {
        return list(namedQuery("com.mt.Core.Store.findAll"));

    }

}
