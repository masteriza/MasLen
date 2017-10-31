package com.maslen.dao;

import com.maslen.dao.interfaces.DriverDao;
import com.maslen.models.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DriverDaoImpl implements DriverDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public DriverDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Route addRoute(Route route) {
//        System.out.println(route);
//        System.out.println(route.toString());

        route.setUserId((int) currentSession().save(route));
//        currentSession().flush();
        return route;


    }

    @Override
    public Route getRoute(int userId) {
        return null;
    }

    @Override
    public List<Route> getAllRoute() {
        List<Route> routes = currentSession().createQuery("from Route").list();
//        List<Route> routez = query.list();

        System.out.println(routes);
        return routes;
    }
}
