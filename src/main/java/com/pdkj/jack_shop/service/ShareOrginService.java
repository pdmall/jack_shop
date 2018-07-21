package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShareOrginService extends BaseService {
    public List<Map<String,Object>> getMyLevel1(Long id,Pager pager) {
        return shareOrginDao.getMyLevel1(id,pager);
    }
    public List<Map<String,Object>> getMyLevel2(Long id ,Pager pager) {
        return shareOrginDao.getMyLevel2(id,pager);
    }
    public Map<String,Object> getMyLevel1Money(Long id) {
        return shareOrginDao.getMyLevel1Money(id);
    }
    public Map<String,Object> getMyLevel2Money(Long id) {
        return shareOrginDao.getMyLevel2Money(id);
    }
}
