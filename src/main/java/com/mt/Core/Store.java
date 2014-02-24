package com.mt.Core;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by bing.du on 2/17/14.
 */
public class Store {
    private String id;
    private String name;
    private String address;

    public Store(String id,String name,String address)
    {
        this.id = id;
        this.name = name;
        this.address =address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
