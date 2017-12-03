package com.maslen.dao;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
//        user.setStatus("I");
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


//    @Override
//    public Optional<User> searchUserByEmail(String username) {
//        Optional user = currentSession()
//                .createQuery("select new User( u.email, u.password, u.role) from User u " +
//                        "inner join Role r on u.role=r.roleId " +
//                        "WHERE u.email =:username")
//                .setParameter("username", username)
//                //.uniqueResult();
//                .uniqueResultOptional();
//        return user;

    @Override
    public Optional<User> searchUserByEmail(String username) {
        return currentSession()
                .createQuery("select new User( u.email, u.password, u.role) from User u " +
                        "inner join Role r on u.role=r.roleId " +
                        "WHERE u.email =:username")
                .setParameter("username", username)
                .uniqueResultOptional();


//        return currentSession()
//                .createQuery("select new User( u.username, u.password, u.role) " +
//                        "from User u inner join Role r on u.role=r.roleId where username =:username", User.class)
//                .setParameter("username", username).uniqueResultOptional();

//        return currentSession()
//                .createQuery("select new User( u.username, u.password, u.role)" +
//                        "from Role r inner join User u on u.role=r.roleId", Role.class);

        //.list();


//        return currentSession()
//                .createQuery("select new User( username, password, role) " +
//                        "from User where username =:username", User.class)
//                .setParameter("username", username).uniqueResultOptional();

    }

    @Override
    public long isRegisteredEmail(String email) {
        return (long) currentSession()
                .createQuery("select count(*) from User where email =:email")
                .setParameter("email", email).uniqueResult();
    }

    @Override
    public long isRegisteredEmailAndActivated(String email) {
        return (long) currentSession()
                .createQuery("select count(*) from User where email =:email")
                .setParameter("email", email).uniqueResult();
    }


    @Override
    public long isRegisteredPhone(String number) {
        return (long) currentSession()
                .createQuery("select count(*) from Phone where number =:number")
                .setParameter("number", number).uniqueResult();
    }

    @Override
    public User activateUser(String email) {
        //todo:ZZZZZZZZZ
        return null;
    }


}
