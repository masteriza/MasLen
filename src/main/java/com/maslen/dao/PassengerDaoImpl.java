package com.maslen.dao;

import com.maslen.dao.interfaces.PassengerDao;
import com.maslen.models.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PassengerDaoImpl implements PassengerDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PassengerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Route addRoute(Route route) {
        route.setUserId((int) currentSession().save(route));
        return route;
    }

    @Override
    public Route getRoute(int userId) {
        return null;
    }

    @Override
    public List<Route> getAllRoute() {
        List<Route> routes = currentSession().createQuery("select distinct r from Route r inner join fetch r.routePoints ", Route.class).list();
        return routes;
    }

    @Override
    public int deleteRoute(int routeId) {
        Route route = (Route) currentSession().createQuery("from Route where routeId = :routeId ").setParameter("routeId", routeId).list().get(0);
        currentSession().delete(route);
        return currentSession().createQuery("delete Route where routeId =:routeId")
                .setParameter("routeId", routeId).executeUpdate();
    }


}






