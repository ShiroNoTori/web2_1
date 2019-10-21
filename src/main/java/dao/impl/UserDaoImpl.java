package dao.impl;

import model.User;
import dao.UserDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Session session;

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    public User getById(int id) {
        User user = session.find(User.class, id);
        session.close();
        return user;
    }

    public List<User> getAll() {
        Query query = session.createQuery("from User");
        List<User> list = query.getResultList();
        session.close();
        return list;
    }

    public boolean remove(int id) {
        session.getTransaction().begin();
        Query query = session.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        int deleteNum = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return deleteNum == 1;
    }

    public boolean add(User user) {
        Transaction tr = session.beginTransaction();
        boolean committed = false;
        try {
            session.save(user);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        committed = tr.getStatus() == TransactionStatus.COMMITTED;
        session.close();
        return committed;
    }

    public boolean update(User user) {
        Transaction tr = session.beginTransaction();
        boolean committed = false;
        session.update(user);
        tr.commit();
        committed = tr.getStatus() == TransactionStatus.COMMITTED;
        session.close();
        return committed;
    }

    @Override
    public boolean hasId(int id) {
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        boolean hasResult = query.getSingleResult() != null;
        session.close();
        return hasResult;
    }
}