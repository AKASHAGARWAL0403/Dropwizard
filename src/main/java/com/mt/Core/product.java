package com.mt.Core;



import com.mt.DB.ProductDao;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by bing.du on 2/17/14.
 */

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(
                name = "com.mt.Core.product.findAll",
                query = "SELECT new product(id,pname) FROM product p"
        ),
        @NamedQuery(
                name = "com.mt.Core.product.findById",
                query = "SELECT id,pname FROM product p WHERE p.id = :id"
        )
})
public class product {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "pname", nullable = true)
    private String pname;
    @Column(name = "pdetail", nullable = true)
    private String pdetail;



    public product()
    {

    }

    public product(int id, String pname)
    {
        this.id = id;
        this.pname = pname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getPname() {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }



}
