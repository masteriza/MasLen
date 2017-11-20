package com.maslen.dao;

import com.maslen.dao.interfaces.PassengerDao;
import com.maslen.models.PassengerSearchRouteDto;
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

    @Override
    public void searchRoute(PassengerSearchRouteDto passengerSearchRouteDto) {
//        List<Route> routes = currentSession().createQuery("select distinct r from Route r inner join fetch r.routePoints ", Route.class).list();
        List<Route> routes = currentSession().createQuery("SELECT\n" +
                "  d.route_id,\n" +
                "  d.userId,\n" +
                "  d.startRouteLatitude,\n" +
                "  d.startRouteLongitude,\n" +
                "  d.finishRouteLatitude,\n" +
                "  d.finishRouteLongitude,\n" +
                "  d.distance\n" +
                "FROM (SELECT\n" +
                "    r.route_id,\n" +
                "    r.userId,\n" +
                "    r.startRouteLatitude,\n" +
                "    r.startRouteLongitude,\n" +
                "    r.finishRouteLatitude,\n" +
                "    r.finishRouteLongitude,\n" +
                "    p.radius,\n" +
                "    p.distance_unit\n" +
                "    * DEGREES(ACOS(COS(RADIANS(p.startlatpoint))\n" +
                "    * COS(RADIANS(r.startRouteLatitude))\n" +
                "    * COS(RADIANS(p.startlongpoint - r.startRouteLongitude))\n" +
                "    + SIN(RADIANS(p.startlatpoint))\n" +
                "    * SIN(RADIANS(r.startRouteLatitude)))) AS distance\n" +
                "  FROM routes AS r\n" +
                "    JOIN (SELECT\n" +
                "        50.4574433 AS startlatpoint,\n" +
                "        30.617243 AS startlongpoint,\n" +
                "        50.4471538 AS endlatpoint,\n" +
                "        30.5307786 AS endlongpoint,\n" +
                "        1.0 AS radius,\n" +
                "        111.045 AS distance_unit) AS p\n" +
                "      ON 1 = 1\n" +
                "  WHERE r.startRouteLatitude BETWEEN p.startlatpoint - (p.radius / p.distance_unit) AND p.startlatpoint + (p.radius / p.distance_unit)\n" +
                "  AND r.startRouteLongitude BETWEEN p.startlongpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint)))) AND p.startlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint))))\n" +
                "\n" +
                "  AND r.finishRouteLatitude BETWEEN p.endlatpoint - (p.radius / p.distance_unit) AND p.endlatpoint + (p.radius / p.distance_unit)\n" +
                "  AND r.finishRouteLongitude BETWEEN p.endlongpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint)))) AND p.endlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint))))) AS d\n" +
                "WHERE distance <= radius\n" +
                "ORDER BY distance\n" +
                "LIMIT 15").list();
        System.out.println(routes);

    }


}






