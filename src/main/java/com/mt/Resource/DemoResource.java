package com.mt.Resource;

/**
 * Created by bing.du on 2/17/14.
 */

import com.google.common.base.Charsets;
import com.mt.Core.TempStores;
import com.mt.DB.IStoreJDBIDao;
import com.mt.DB.StoreDao;
import com.mt.View.BaseView;
import com.mt.Core.Store;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    private final IStoreJDBIDao storeJDBIDao;

    public DemoResource(StoreDao storeDAO, IStoreJDBIDao storeJDBIDao) {
        charset = Charsets.UTF_8;
        this.storeDAO = storeDAO;
        this.storeJDBIDao = storeJDBIDao;
    }

    @GET
    @Timed
    @UnitOfWork
    public BaseView get() {
        List<Store> stores;
        if (storeJDBIDao != null) {
            stores = storeJDBIDao.findAll();
        } else if (storeDAO != null) {
            stores = storeDAO.findAll();
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
                    @FormParam("address") String address) throws IOException {
        if (storeJDBIDao != null) {
            storeJDBIDao.add(id, name, address);
        } else if (storeDAO != null) {
            storeDAO.create(new Store(id, name, address));
        } else {
            new TempStores().storeList.add(new Store(id, name, address));
        }

        response.sendRedirect("/demo");


    }


}
