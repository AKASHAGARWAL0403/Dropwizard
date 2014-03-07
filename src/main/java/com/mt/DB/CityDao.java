package com.mt.DB;

import com.mt.Core.City;
import com.mt.Core.product;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by bing.du on 3/7/14.
 */
public class CityDao extends AbstractDAO<City> {
    public CityDao(SessionFactory factory) {
        super(factory);
    }

    public City findById(int id) {
        return get(id);
    }

    public List<City> findAll() {
        return list(namedQuery("com.mt.Core.City.findAll"));

    }
}