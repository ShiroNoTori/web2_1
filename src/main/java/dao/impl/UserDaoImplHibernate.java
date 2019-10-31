package dao.impl;

import dao.UserDao;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import util.DBHelper;
import util.HibernateUtility;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoImplHibernate implements UserDao {

    private Session session;

    public UserDaoImplHibernate(DBHelper helper) {
        this.session = HibernateUtility.getSessionFactory(helper.getConfiguration()).openSession();
    }

    @Override
    public User getById(int id) {
        User user = session.find(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Query query = session.createQuery("from User");
        List<User> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public boolean remove(int id) {
        session.getTransaction().begin();
        Query query = session.createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        int deleteNum = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return deleteNum == 1;
    }

    @Override
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

    @Override
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

    @Override
    public User getUser(String login, String password) {
        User user = null;

        Query query = session.createQuery("from User u where login =:login and password =:password");
        query.setParameter("login", login);
        query.setParameter("password", password);

        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException ignored) {
        }

        session.close();
        return user;
    }
}
