package com.example.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.example.utils.Direction;
import com.example.utils.Page;


/**
 * 业务层接口的基接口, 定义常用的业务方法
 * @author anonymous
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {
	/**
	 * 保存对象
	 * @param t
	 */
	public void save(T t);
	
	
	/**
	 * 根据Id删除对应的对象
	 * @param id
	 */
	public void delete(T t);
	
	
	/**
	 * 根据Id删除对应的对象
	 * @param id
	 */
	public void delete(PK id);
	
	
	/**
	 * 更新某个对象
	 * @param t
	 */
	public void update(T t);
	
	
	/**
	 * 根据id获取某个对象
	 * @param id
	 * @return
	 */
	public T getById(PK id);
	
	
	/**
	 * 获取所有对象
	 * @return
	 */
	public List<T> getAll();
	
	
	/**
	 * 根据对象的属性查询一个对象,此属性值必须是唯一的,所有只返回一个唯一的对象
	 * @param propertyName 属性值
	 * @param propertyValue 属性名
	 * @return
	 */
	public T getByProperty(String propertyName, Object propertyValue);
	
	
	/**
	 * 根据对象的属性查询满足该属性值的所有对象,
	 * 此属性值不是唯一的,所有返回的是对象的List集合
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return
	 */
	public List<T> getsByProperty(String propertyName, Object propertyValue);
	
	
	/**
	 * 根据对象的属性查询满足该属性值的所有对象,
	 * 此属性值不是唯一的,所有返回的是对象的List集合
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @param orders 用于排序的属性以及排序方式的集合,无需排序则传入Null
	 * @return
	 */
	public List<T> getsByProperty(String propertyName, Object propertyValue, LinkedHashMap<String, Direction> orders);
	
	
	/**
	 * 分页查询
	 * @param page  页码,从0开始取
	 * @param pageSize  每页显示多少条记录, 即从数据库中取多少条记录 
	 * @param orders  排序条件,集合中的key需要与对象的属性名保持一致
	 * @param propertyName  条件查询的属性名
	 * @param propertyValue  条件查询属性的属性值
	 * @return
	 */
	public Page<T> getPage(Integer page, Integer pageSize, LinkedHashMap<String, Direction> orders, String propertyName, Object propertyValue);
	
	
	/**
	 * 分页查询
	 * @param page  页码,从0开始取
	 * @param pageSize  每页显示多少条记录, 即从数据库中取多少条记录 
	 * @return
	 */
	public Page<T> getPage(Integer page, Integer pageSize);
	
	
	/**
	 * 根据id更新数据库表中对应字段的值
	 * @param id 需要修改的对象/数据库记录的id
	 * @param propertyName  属性名,与对象的属性名称保持一致
	 * @param propertyValue  该属性需要修改的值
	 * @return
	 */
	public int updatePropertyById(PK id, String propertyName, Object propertyValue);
	
	
	/**
	 * 根据id及属性名获取属性值
	 * @param id
	 * @param propertyName 属性名
	 * @return
	 */
	public <E> E getPropertyValueById(PK id, String propertyName);
	
	
	/**
	 * 模糊查询 
	 * @param key 查询的关键字
	 * @param property 模糊查询的字段
	 */
	public List<T> like(String propertyName, String key);
	
	
	/**
	 * 随机获取多个对象
	 * @param count 随机获取的对象个数
	 * @return
	 */
	public Set<T> getRandomData(Integer count);
	
	
	/**
	 * 随机获取单个对象
	 * @return
	 */
	public T getRandomEntity();
}
