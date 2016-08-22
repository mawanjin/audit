package com.ljs.util;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Brody Liao
 * @version 1.0
 */
@Repository
public class HibernateUtil{

    @Autowired
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate;
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }



    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {

        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }

    /**
     * 使用hql 语句进行操作
     * @param hql     需要执行的hql语句
     * @param offset  设置开始位置
     * @param length  设置读取数据的记录条数
     * @return List   返回所需要的集合。
     */
    public List getListForPage(final String hql, final int offset,
                               final int length) {
        List list1 = getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return getList(session,hql,offset,length);
            }
        });
        return list1;
    }

    /**
     * @param             session :一个会话
     * @param            hql:是需要执行的hql语句，
     * @param            offset 设置开始位置
     * @param              length:读取记录条数
     * return             返回结果集List<?>表示一个泛型的List
     */
    public static List<?> getList( Session session , String hql , int offset, int length){
        Query q = session.createQuery(hql);
        q.setFirstResult(offset);
        q.setMaxResults(length);

        Logger.getGlobal().info(hql);
        List<?> list = q.list();
        Logger.getGlobal().info("取到的每页的size"+list.size());
        return list;
    }
}