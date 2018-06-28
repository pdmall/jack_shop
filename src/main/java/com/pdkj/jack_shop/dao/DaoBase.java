package com.pdkj.jack_shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class DaoBase<E> {
    protected RowMapper<E> rowMap;
    protected String tableName;
    protected String columnQ;
    protected String columnU;
    protected String sqlSel = "select "+columnQ+ "from "+tableName+" where ";
    protected String sqlIns = "insert "+columnU+ "from "+tableName+" where ";
    protected String sqlUpd = "update "+columnU+ "from "+tableName+" where ";
    protected String sqlDel ;
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * @Title: deleteByIds
     * @Description: 根据Id删除多个
     */
    public Integer deleteByIds(Long[] ids) {
        sqlDel +="(";
        for (int i = 0 ; ids.length > 0 ; i++) {
            sqlDel +=ids[i]+",";
        }
        sqlDel +=")";
        return jdbcTemplate.update(sqlDel,ids);
    }

    /**
     * @Title: save
     * @Description: 添加
     */
    public  Integer save(Object[] pramts)  {
        return jdbcTemplate.update(sqlIns,pramts);
    };

    /**
     * @Title: update
     * @Description: 修改
     */
    public  Integer update(Object[] pramts) {
        return jdbcTemplate.update(sqlUpd,pramts);
    };
    /**
     * @Title: findById
     * @Description: 根据Id查询数据
     */
    public E findById(Long id) {
        List<E> model = jdbcTemplate.query(sqlSel,new Object[]{"1"},rowMap);
        if (model.size() == 0) {
            return null;
        }
        return model.get(0);
    };
    /**
     * @Title: findByCondtion
     * @Description: 条件查询数据
     */
    public List<E> findByCondtion(Object[] parameters)  {
        return jdbcTemplate.query(sqlSel,parameters,rowMap);
    };

}
