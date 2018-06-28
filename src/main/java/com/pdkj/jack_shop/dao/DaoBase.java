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
    protected String sqlDel = "delete from "+tableName+" where id  in ";
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * @Title: deleteById
     * @Description: 根据Id删除数据
     */
    public Integer deleteById(Long id)  {
        return jdbcTemplate.update(sqlDel,id);
    };

    /**
     * @Title: deleteByIds
     * @Description: 根据Id删除多个
     */
    public Integer deleteByIds(Long[] ids) {
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
