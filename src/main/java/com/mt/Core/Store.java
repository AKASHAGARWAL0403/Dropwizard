package com.mt.Core;


import com.mt.DB.ProductDao;
import org.hibernate.annotations.Fetch;

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
                query = "FROM Store p left join fetch p.relatedProduct"
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

    @Column(name = "productId", nullable = false)
    private int productId;

    @ManyToOne(targetEntity = product.class)
    @JoinColumn(name = "productId",insertable = false, updatable = false )
    private product relatedProduct;

    public product getProduct() {
        return relatedProduct;
    }

    public void setType(product relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public Store() {

    }

    public Store(int id, String name, String address, product relatedProduct) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.relatedProduct = relatedProduct;
        //this.relatedProduct.setId(productId);
    }

    public Store(int id, String name, String address, int productId) {
        this.id = id;
        this.name = name;
        this.address = address;
        //this.productId = productId;
        //this.relatedProduct.setId(productId);
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

    public int getProductId() {
        return relatedProduct.getId();
    }

    public String getProductName() {
        return relatedProduct.getPname();
    }


    public void setid(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setaddress(String address) {
        this.address = address;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }



}
