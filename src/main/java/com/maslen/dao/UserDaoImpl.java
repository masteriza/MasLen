package com.maslen.dao;

import com.maslen.dao.interfaces.UserDao;
import com.maslen.models.User;
import com.maslen.models.UserActivity;
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
        user.setUserId((long) currentSession().save(user));
        return user;
    }

//    @Override
//    public User findUserById(int userId) {
//
//        User user = (User) currentSession().get(User.class, userId);
////        user.setFirstName(String.valueOf(new java.util.Date()));
////        currentSession().save(user);
//
//        return user;
//    }


//    @Override
//    public Optional<User> getUserByEmail(String username) {
//        Optional user = currentSession()
//                .createQuery("select new User( u.email, u.password, u.role) from User u " +
//                        "inner join Role r on u.role=r.roleId " +
//                        "WHERE u.email =:username")
//                .setParameter("username", username)
//                //.uniqueResult();
//                .uniqueResultOptional();
//        return user;

    @Override
    public User getUserByEmail(String email) {
        return currentSession().createQuery("select distinct u from User u inner join fetch u.authorities WHERE u.email =:email", User.class).setParameter("email", email).uniqueResult();
    }

    @Override
    public boolean isRegisteredEmail(String email) {
        long rowCount = (long) currentSession()
                .createQuery("select count(*) from User where email =:email")
                .setParameter("email", email).uniqueResult();
        return (rowCount > 0) ? true : false;
    }

    @Override
    public boolean isConfirmationEmail(String email) {
        long rowCount = (long) currentSession()
                .createQuery("select count(*) from User where email =:email and isActivated = true")
                .setParameter("email", email).uniqueResult();
        return (rowCount > 0) ? true : false;
    }


    @Override
    public boolean isRegisteredPhone(String number) {
        long rowCount = (long) currentSession()
                .createQuery("select count(*) from Phone where number =:number")
                .setParameter("number", number).uniqueResult();
        return (rowCount > 0) ? true : false;
    }

    @Override
    public boolean activateUser(String email) {
        long rowCount = (long) currentSession().createQuery("update User set isActivated = 1 where email =:email")
                .setParameter("email", email).executeUpdate();
        return (rowCount > 0) ? true : false;
    }

    @Override
    public boolean addUserActivity(UserActivity userActivity) {
        long idInsertRow = (long) currentSession().save(userActivity);
        return (idInsertRow > 0) ? true : false;
    }

    @Override
    public boolean isValidSessionForUserId(String userId, String session) {
        long rowCount = (long) currentSession()
                .createQuery("select count(*) from User u inner join FETCH UserActivity ua ON u.userId = ua.user " +
                        "where u.userId =:userId and ua.session =:session and isActivated = true and NOW() < ua.endDate")
                .setParameter("userId", Long.parseLong(userId))
                .setParameter("session", session)
                .uniqueResult();
        return (rowCount > 0) ? true : false;
    }

    @Override
    public boolean updateUserPassword(String userId, String encryptPassword) {
        long rowCount = (long) currentSession()
                .createQuery("update User u set u.password = :encryptPassword where u.userId =:userId")
                .setParameter("userId", userId)
                .setParameter("encryptPassword", encryptPassword)
                .executeUpdate();
        return (rowCount > 0) ? true : false;
    }


}
