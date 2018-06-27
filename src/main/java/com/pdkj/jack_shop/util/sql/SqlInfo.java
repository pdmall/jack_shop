package com.pdkj.jack_shop.util.sql;

import java.util.List;

public class SqlInfo {
    private String sql;
    private List<Object> values;

    public SqlInfo(String sql, List<Object> values) {
        this.sql = sql;
        this.values = values;
    }

    public String getSql() {
        return sql;
    }

    @Override
    public String toString() {
        return sql;
    }

    public Object[] getValues() {
        return values.toArray();
    }
}