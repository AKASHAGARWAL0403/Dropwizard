package com.mt.Core;


import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by bing.du on 2/17/14.
 */

@Entity
@Table(name = "store")
@NamedQueries({
        @NamedQuery(
                name = "com.mt.Core.Store.findAll",
                query = "SELECT new Store(id,name,address) FROM Store p"
        ),
        @NamedQuery(
                name = "com.mt.Core.Store.findById",
                query = "SELECT id,name,address FROM Store p WHERE p.id = :id"
        )
})
public class Store {

    @Id
    private int id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "address", nullable = true)
    private String address;

    public Store()
    {

    }

    public Store(int id,String name,String address)
    {
        this.id = id;
        this.name = name;
        this.address =address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setid(int id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setaddress(String address)
    {
        this.address = address;
    }


}
