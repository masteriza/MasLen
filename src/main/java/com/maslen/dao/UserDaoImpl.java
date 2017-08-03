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
    public User addUser(User user) {
        user.setStatus("I");
        user.setUserId((int) currentSession().save(user));
        return user;
    }

    @Override
    public User findUserById(int userId) {

        User user = (User) currentSession().get(User.class, userId);
//        user.setFirstName(String.valueOf(new java.util.Date()));
//        currentSession().save(user);

        return user;
    }
}
