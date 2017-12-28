package com.maslen.security.repository;

import com.maslen.models.User;
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

        //.createQuery("select new User (u.id, u.username, u.password, u.firstname, u.lastname, u.email, u.enabled, u.lastPasswordResetDate, u.authorities) from User u " +
//        return (User) currentSession()
//                .createQuery("select new User (u.id, u.username, u.password, u.firstname, u.lastname, u.email, u.enabled, u.lastPasswordResetDate, u.authorities) from User u " +
//                        //"INNER join fetch Authority a " +
//                        "WHERE u.email =:username")
//                .setParameter("username", username)
//                .uniqueResult();

//        Query query = currentSession().createNativeQuery("SELECT u. FROM USER u INNER JOIN AUTHORITY a ON u.ID = a.ID");

//                       "INNER join fetch Authority a " +
//                       "WHERE u.email =:username");
        //User user = (User)

        //User user = (User) currentSession().get(User.class, 1);
        User user = (User) currentSession().createQuery(" from User u where  u.email = :username").setParameter("username", username).uniqueResult();
//        List<User> users = currentSession().createQuery(" select u.id,u.authorities from User u").list();

        return user;
    }
}
