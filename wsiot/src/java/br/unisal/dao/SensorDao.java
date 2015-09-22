/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.dao;

import br.unisal.hibernate.util.HibernateUtil;
import br.unisal.interfaces.SensorInterface;
import br.unisal.model.Sensor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jether
 */
public class SensorDao implements SensorInterface{

    @Override
    public void insert(Sensor s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.save(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception SensorDao.insert(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
    @Override
    public void update(Sensor s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.update(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception SensorDao.update(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Sensor> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Sensor> sensores = new ArrayList<>();
        try {
            tx.begin();
            Query query = session.createQuery("FROM Sensor");
            sensores = query.list();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception SensorDao.getAll(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
        return sensores;
    }
    
    @Override
    public Sensor getById(Sensor s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Sensor sensor = new Sensor();
        try {
            tx.begin();
            Query query = session
                    .createQuery("FROM Sensor WHERE idSensor = :id");
            query.setParameter("id", s.getIdSensor());
            sensor = (Sensor) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception SensorDao.getById(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
        return sensor;
    }

    @Override
    public void remove(Sensor s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.delete(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception SensorDao.remove(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
}
