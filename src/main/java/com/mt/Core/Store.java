package com.mt.Core;


import javax.persistence.*;

/**
 * Created by bing.du on 2/17/14.
 */

@Entity
@Table(name = "store")
@NamedQueries({
        @NamedQuery(
                name = "com.mt.Core.Store.findAll",
                query = "FROM Store p left join fetch p.relatedCity"
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

    @Column(name = "cityId", nullable = false)
    private int cityId;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "cityId",insertable = false, updatable = false)
    private City relatedCity;

    public City getProduct() {
        return relatedCity;
    }

    public void setType(City relatedCity) {
        this.relatedCity = relatedCity;
    }

    public Store() {

    }

    public Store(int id, String name, String address, City relatedCity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.relatedCity = relatedCity;
        //this.relatedCity.setId(productId);
    }

    public Store(int id, String name, String address, int cityId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cityId = cityId;
        //this.relatedCity.setId(productId);
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

    public int getCityId() {
        return relatedCity.getId();
    }

    public String getCityName() {
        return relatedCity.getCname();
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


    public void setCityId(int CityId) {
        this.cityId = CityId;
    }



}
