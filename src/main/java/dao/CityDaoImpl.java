package dao;

import Hibernate.HibernateSessionFactoryUtil;
import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDaoImpl implements CityDao{
    @Override
    public Integer create(City city) {
        int result;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            result = (Integer) session.save(city);
            transaction.commit();
        }
        return result;
    }

    @Override
        public City findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City", City.class).list();
        }
    }

    @Override
    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
