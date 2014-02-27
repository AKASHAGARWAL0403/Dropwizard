package com.mt.DB;

import com.mt.Core.Store;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bing.du on 2/25/14.
 */
public class StoreMapper implements ResultSetMapper<Store> {
    @Override
    public Store map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new Store(r.getInt("id"), r.getString("name"),r.getString("address"));
    }
}