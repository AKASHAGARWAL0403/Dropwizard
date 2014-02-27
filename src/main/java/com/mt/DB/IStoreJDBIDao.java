package com.mt.DB;

import com.mt.Core.Store;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by bing.du on 2/25/14.
 */
@RegisterMapper(StoreMapper.class)
public interface IStoreJDBIDao
{
    @SqlQuery("select id, name, address from store ")
    List<Store> findAll();

    @SqlUpdate("insert into store (id, name, address) values (:id, :name, :address)")
    void add(@Bind("id") int id, @Bind("name") String name,@Bind("address") String address);

    @SqlUpdate("update store set name= :name ,address = :address where id = :id")
    void update(@Bind("id") int id, @Bind("name") String name,@Bind("address") String address);

    @SqlUpdate("delete from store where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select id, name, address from store where id= :id")
    List<Store> findStoreById(@Bind("id") int id);
}