package com.pdkj.jack_shop.core;


import com.pdkj.jack_shop.model.User;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Autowired
    RedisTemplate redisTemplate;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public Object getCache(String key){
        Object value = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            value = operations.get(key);
        }
        return value;
    }

    public void setCache(String key,Object value,int seconds){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setCache(String key,Object value){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, 10, TimeUnit.SECONDS);
    }

    public void save(T model) {
        mapper.insertSelective(model);
    }

    public void save(List<T> models) {
        mapper.insertList(models);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }

    public List<T> findByCondition(Map<String,Object> map){
        return mapper.findByCondition(map);
    }
    public T findById(Long id){
        return mapper.findById(id);
    }
}
