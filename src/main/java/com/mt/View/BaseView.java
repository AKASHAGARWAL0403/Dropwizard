package com.mt.View;

//import com.mt.DB.StoreDao;

import com.mt.Core.StoreEx;
import com.mt.Core.TempStores;
import com.mt.DB.IStoreJDBIDao;
import com.mt.DB.StoreDao;
import com.yammer.dropwizard.views.View;
import com.mt.Core.Store;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.du on 2/17/14.
 */
public class BaseView extends View {
    private static final String TEMPLATE_PATH_FORMAT = "/views/page/%s.ftl";
    private StoreDao storeDAO;
    private IStoreJDBIDao storeJDBIDao;
    private ArrayList<Store> stores;

    public BaseView(String templateName, Charset charset, StoreDao store, IStoreJDBIDao storeJDBIDao,ArrayList<Store> stores) {
        super(String.format(TEMPLATE_PATH_FORMAT, templateName), charset);
        this.storeDAO = store;
        this.storeJDBIDao = storeJDBIDao;
        this.stores = stores;

    }

    public ArrayList<Store> getStores() {
        return stores;
        //if (storeJDBIDao != null) {
            //return storeJDBIDao.findAll();
        //} else if (storeDAO != null) {
        //    return storeDAO.findAll();
        //} else {
         //   return new TempStores().storeList;
        }
    }



