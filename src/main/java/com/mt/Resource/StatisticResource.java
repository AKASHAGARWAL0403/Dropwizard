package com.mt.Resource;

import com.mt.Core.Store;
import com.mt.Core.Stores;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by bing.du on 2/17/14.
 */
@Path("/api/stat")
public class StatisticResource {

    public StatisticResource() {
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Store> viewCrewStat() {
        return new Stores().storeList;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean add(@FormParam("id") String id,
                                @FormParam("name") String name,
                                @FormParam("address") String address) {
        new Stores().storeList.add(new Store(id,name,address));
        return true;
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean delete(@FormParam("id") String id) {
        for (Store store : new Stores().storeList) {
            if(store.getId().equals(id))
            {
                new Stores().storeList.remove(store);
            }
        }
        return true;
    }

    @POST
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public void edit(@FormParam("id") String id,
                     @FormParam("name") String name,
                     @FormParam("address") String address) {
        for (Store store : new Stores().storeList) {
            if(store.getId().equals(id))
            {
                new Stores().storeList.remove(store);
                new Stores().storeList.add(new Store(id,name,address));
            }
        }
    }

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Store search(@FormParam("id") String id) {
        for (Store store : new Stores().storeList) {
            if(store.getId().equals(id))
            {
                return store;
            }
            }
        return null;
    }


    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Store GetStore(@QueryParam("id") String id) {
        for (Store store : new Stores().storeList) {
            if(store.getId().equals(id))
            {
                return store;
            }
        }
        return null;
    }

}
