package com.maslen.security.repository;

import com.maslen.security.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public User findByUsername(String username) {

        return (User) currentSession()
                .createQuery("select new User (u.id, u.username, u.password, u.firstname, u.lastname, u.email, u.enabled, u.lastPasswordResetDate, u.authorities) from User u " +
                        "join Authority a on a.id = u.authorities " +
                        "WHERE u.email =:username")
                .setParameter("username", username)
                .uniqueResult();
    }
}
