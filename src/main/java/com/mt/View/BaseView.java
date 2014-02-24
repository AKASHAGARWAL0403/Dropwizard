package com.mt.View;

import com.yammer.dropwizard.views.View;
import com.mt.Core.Store;
import com.mt.Core.Stores;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by bing.du on 2/17/14.
 */
public class BaseView extends View  {
    private static final String TEMPLATE_PATH_FORMAT = "/views/page/%s.ftl";
    private  Store store;
    private ArrayList<Store> stores;

    public BaseView(String templateName, Charset charset,Store store) {
        super(String.format(TEMPLATE_PATH_FORMAT, templateName), charset);
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public ArrayList<Store> getStores() { return new Stores().storeList; }

}
