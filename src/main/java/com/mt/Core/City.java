package com.mt.Core;

import javax.persistence.*;

/**
 * Created by bing.du on 3/7/14.
 */
@Entity
@Table(name = "City")
@NamedQueries({
        @NamedQuery(
                name = "com.mt.Core.City.findAll",
                query = "SELECT new City(id,Cname) FROM City p"
        )
})
public class City {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "Cname", nullable = true)
    private String Cname;

    public City(){}

    public City(int id, String cname) {
        this.id = id;
        Cname = cname;
    }

    public int getId() {
        return id;
    }

    public String getCname() {
        return Cname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCname(String cname) {
        Cname = cname;
    }
}
