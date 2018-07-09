package com.pdkj.jack_shop.util.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pdkj.jack_shop.core.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySql {

    StringBuffer sql;
    List<Object> values = new ArrayList<>();

    public MySql(String s) {
        sql = new StringBuffer(s);
        sql.append(" ");
    }

    public MySql() {
        sql = new StringBuffer();
    }

    public void append(String s) {
        sql.append(" ").append(s).append(" ");
    }

    public void limit(Pager pager) {
        sql.append(" limit ");
        sql.append((pager.getPage() - 1) * pager.getRow());
        sql.append(",");
        sql.append(pager.getRow()).append(" ");

    }

    public void orderBy(String... columns) {
        if (columns.length == 0) return;
        sql.append(" order by ");
        for (String column : columns) {
            sql.append(column).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" ");
    }

    public void orderByDesc(String... columns) {
        if (columns.length == 0) return;
        sql.append(" order by ");
        for (String column : columns) {
            sql.append(column).append(" desc,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" ");
    }




    @Override
    public String toString() {

        for (int i=0; i<values.size(); i++){
            System.out.print(values.get(i).toString());
        }
        return sql.toString();
    }

    public void append(String s, Object... keys) throws CustomException {
        List<Object> temp = new ArrayList<>();
        for (Object key : keys) {
            if (StringUtils.isNotEmpty(key.toString())&&!key.toString().contains("null")) {
                temp.add(key);
            } else {
                throw new CustomException("参数异常");
            }
        }
        append(s);
        values.addAll(temp);
    }

    public void notNullAppend(String s, Object... keys) {
        boolean isOk = true;
        List<Object> temp = new ArrayList<>();
        for (Object key : keys) {
            if (StringUtils.isNotEmpty(key.toString())&&!key.toString().contains("null")) {
                temp.add(key);
            } else {
                isOk = false;
                break;
            }
        }
        if (isOk) {
            append(s);
            values.addAll(temp);
        }
    }


    public Object[] getValues() {
        return values.toArray();
    }

    public void addValue(Object value) {
        values.add(value);
    }

    public void clean() {
        sql = new StringBuffer();
        values.clear();
    }

    public void cleanValues() {
        values.clear();
    }

    public long getCount(JdbcTemplate jdbcTemplate) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) num ").append(sql.substring(sql.indexOf("from"),sql.length()));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sb.toString(), getValues());
        return (long)maps.get(0).get("num");
    }

    public void update(String s) {
        sql.append(s);
    }


}
