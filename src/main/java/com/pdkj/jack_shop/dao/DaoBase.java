package com.pdkj.jack_shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoBase<T,ID> {

    @Autowired
    JdbcTemplate jdbcTemplate;


}
