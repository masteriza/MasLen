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
        List<Route> routes = currentSession().createQuery("select distinct r from Route r inner join fetch r.routePoints ", Route.class).list();
        //List<Route> routes = currentSession().createQuery("from Route", Route.class).list();
//        for (Route route : routes) {
//
//            for (RoutePoint routePoint : route.getRoutePoints()) {
//                System.out.println(routePoint);
//            }
//
//
//        }
        System.out.println(routes);
        return routes;
    }


//----------


//    List<Person> personList = session.createQuery("FROM Person").list();
//
//
//                for(
//    Iterator iterator = personList.iterator();
//    iterator.hasNext();
//    )
//
//    {
//        Person person = (Person) iterator.next();
//        Set<Phone> ph = new HashSet<Phone>();
//        ph = person.getPersonPhoneNumbers();
//        for (Phone p : ph) {
//            System.out.println("***************************");
//            System.out.println(person.getName());
//            System.out.println(" Phone Type: " + p.getPhonetype());
//            System.out.println(" Phone Number: " + p.getContactnumber());
//            System.out.println("***************************");
//        }
//    }


//----------
}






