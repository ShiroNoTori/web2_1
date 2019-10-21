package dao.impl;

import model.User;
import dao.UserDao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    public User getById(int id) {
        return em.find(User.class, id);
    }

    public List<User> getAll() {
        Query query = em.createQuery("from User");
        return query.getResultList();
    }

    public boolean remove(int id) {
        em.getTransaction().begin();
        Query query = em.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        int deleteNum = query.executeUpdate();
        em.getTransaction().commit();
        return deleteNum == 1;
    }

    public boolean add(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (EntityExistsException e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}