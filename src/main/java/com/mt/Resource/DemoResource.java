package com.mt.Resource;

/**
 * Created by bing.du on 2/17/14.
 */

import com.google.common.base.Charsets;
import com.mt.Core.StoreEx;
import com.mt.Core.TempStores;
import com.mt.Core.product;
import com.mt.DB.IStoreJDBIDao;
import com.mt.DB.ProductDao;
import com.mt.DB.StoreDao;
import com.mt.View.BaseView;
import com.mt.Core.Store;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Path("/demo")
public class DemoResource {

    private static final String HOMEVIEW = "demo";
    private static final String STOREVIEW = "store";
    private static final String STORELIST = "storelist";
    private final Charset charset;
    private final StoreDao storeDAO;
    private final ProductDao productDao;
    private final IStoreJDBIDao storeJDBIDao;


    public DemoResource(StoreDao storeDAO, ProductDao productDao, IStoreJDBIDao storeJDBIDao) {
        charset = Charsets.UTF_8;
        this.storeDAO = storeDAO;
        this.productDao = productDao;
        this.storeJDBIDao = storeJDBIDao;
    }

    @GET
    @Timed
    @UnitOfWork
    public BaseView get() {
        List<Store> stores;
        //List<StoreEx> storeExs = new ArrayList<StoreEx>();
        if (storeJDBIDao != null) {
            stores = storeJDBIDao.findAll();
        } else if (storeDAO != null) {
            stores = storeDAO.findAll();
            /*
            for (Store store : stores) {
                product p1 = store.getProduct();
                StoreEx storeEx = new StoreEx(store);
                product p = productDao.findById(store.getProductId());
                if (p != null) {
                    storeEx.setProductName(p.getPname());
                }
                storeExs.add(storeEx);
            }
            */
        } else {
            stores = new TempStores().storeList;
        }
        return new BaseView(STORELIST, charset, storeDAO, storeJDBIDao, (ArrayList<Store>) stores);
    }

    @POST
    @Path("/add")
    @Timed
    @UnitOfWork
    public void add(@Context HttpServletResponse response,
                    @FormParam("id") int id,
                    @FormParam("name") String name,
                    @FormParam("address") String address,
                    @FormParam("cityId") int cityId) throws IOException {
        if (storeJDBIDao != null) {
            storeJDBIDao.add(id, name, address);
        } else if (storeDAO != null) {
            storeDAO.create(new Store(id, name, address, cityId));
        } else {
            new TempStores().storeList.add(new Store(id, name, address, cityId));
        }

        response.sendRedirect("/demo");


    }


}
