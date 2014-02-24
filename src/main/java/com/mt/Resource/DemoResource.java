package com.mt.Resource;

/**
 * Created by bing.du on 2/17/14.
 */

import com.google.common.base.Charsets;
import com.mt.View.BaseView;
import com.mt.Core.Store;
import com.mt.Core.Stores;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

@Path("/demo")
public class DemoResource {

    private static final String HOMEVIEW = "demo";
    private static final String STOREVIEW = "store";
    private static final String STORELIST = "storelist";
    private final Charset charset;

    public DemoResource() {
        charset = Charsets.UTF_8;
    }

    @GET
    public BaseView get() {
            return new BaseView(STORELIST, charset,null);
    }

    @POST
    @Path("/add")
    public void add(@FormParam("id") String id,
                        @FormParam("name") String name,
                        @FormParam("address") String address) {
        new Stores().storeList.add(new Store(id,name,address));
        try
        {
            URI uri = new URI("/demo");
            Response response = Response.seeOther(uri).build();
            throw new WebApplicationException(response);
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
            //return new BaseView(STORELIST, charset,null);
        }

    }




}
