package com.pdkj.jack_shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class DaoBase<E> {
    @Autowired
    JdbcTemplate jdbcTemplate;


}
