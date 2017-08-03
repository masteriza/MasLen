package com.maslen.dao;

import com.maslen.beans.User;
import com.maslen.dao.Inf.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public int addUser() {
        return 0;
    }

    @Override
    public User findUserById(int userId) {
        //currentSession().beginTransaction();
        User user = (User) currentSession().load(User.class, userId);
        //currentSession().getTransaction().commit();
        //currentSession().close();

//        return currentSession()
//                .createQuery("from User where user_id =:userId", User.class)
//                .setParameter("user_id", userId).uniqueResult();

        return user;
    }
}
