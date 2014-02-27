package com.mt.Resource;

import com.mt.Core.Store;
import com.mt.Core.TempStores;
import com.mt.DB.IStoreJDBIDao;
import com.mt.DB.StoreDao;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by bing.du on 2/17/14.
 */
@Path("/api/stat")
public class ActionResource {
    private final StoreDao storeDAO;
    private final IStoreJDBIDao storeJDBIDao;

    public ActionResource(StoreDao storeDAO,IStoreJDBIDao storeJDBIDao) {
        this.storeDAO = storeDAO;
        this.storeJDBIDao = storeJDBIDao;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public ArrayList<Store> viewCrewStat() {
        if (storeJDBIDao != null) {
            return (ArrayList<Store>)storeJDBIDao.findAll();
        } else if (storeDAO != null) {
            return (ArrayList<Store>)storeDAO.findAll();
        } else {
            return new TempStores().storeList;
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public boolean add(@FormParam("id") int id,
                                @FormParam("name") String name,
                                @FormParam("address") String address) {
        if (storeJDBIDao != null) {
             storeJDBIDao.add(id,name,address);
        } else if (storeDAO != null) {
            storeDAO.create(new Store(id,name,address));
        } else {
            new TempStores().storeList.add(new Store(id,name,address));
        }
        return true;
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public boolean delete(@FormParam("id") int id) {
        if (storeJDBIDao != null) {
            storeJDBIDao.delete(id);
        } else if (storeDAO != null) {
            storeDAO.delete(id);
        } else {
            for (Store store : new TempStores().storeList) {
                if(store.getId()==id)
                {
                    new TempStores().storeList.remove(store);
                }
            }
        }

        return true;
    }

    @POST
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public void edit(@FormParam("id") int id,
                     @FormParam("name") String name,
                     @FormParam("address") String address) {
        if (storeJDBIDao != null) {
            storeJDBIDao.update(id, name, address);
        } else if (storeDAO != null) {
            Store currentStore = storeDAO.findById(id);
            currentStore.setName(name);
            currentStore.setaddress(address);
            storeDAO.edit(currentStore);
        } else {
            for (Store store : new TempStores().storeList) {
                if(store.getId()==id)
                {
                    new TempStores().storeList.remove(store);
                    new TempStores().storeList.add(new Store(id,name,address));
                }
            }
        }
    }



    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Store search(@FormParam("id") int id) {
        if (storeJDBIDao != null) {
            Store target= storeJDBIDao.findStoreById(id).get(0);
            return target;
        } else if (storeDAO != null) {
           return (Store)storeDAO.findById(id);
        } else {
                for (Store store : new TempStores().storeList) {
                    if(store.getId()==id)
                    {
                        return store;
                    }
                }
        }

        return null;
    }


    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Store GetStore(@QueryParam("id") int id) {
        for (Store store : new TempStores().storeList) {
            if(store.getId()==id)
            {
                return store;
            }
        }
        return null;
    }

}
