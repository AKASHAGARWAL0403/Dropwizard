package com.mt.DB;

import com.mt.Core.product;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by bing.du on 3/4/14.
 */
public class ProductDao extends AbstractDAO<product> {
    public ProductDao(SessionFactory factory) {
        super(factory);
    }

    public product findById(int id) {
        return get(id);
    }

    public List<product> findAll() {
        return list(namedQuery("com.mt.Core.product.findAll"));

    }
}