package com.mt.Core;

/**
 * Created by bing.du on 3/5/14.
 */
public class StoreEx extends Store {
    private String productName;

    public StoreEx(Store store)
    {
        setid(store.getId());
        setName(store.getName());
        setaddress(store.getAddress());
        //setProductId(store.getProductId());
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
