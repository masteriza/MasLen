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
        List<Route> routes = currentSession().createNativeQuery("SELECT\n" +
                "  d.routeId,\n" +
                "  d.userId,\n" +
                "  d.startRouteLatitude,\n" +
                "  d.startRouteLongitude,\n" +
                "  d.finishRouteLatitude,\n" +
                "  d.finishRouteLongitude,\n" +
                "  d.distanceBetweenStartPoints,\n" +
                "  d.distanceBetweenFinishPoints\n" +
                "FROM (SELECT\n" +
                "    r.routeId,\n" +
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
                "    * SIN(RADIANS(r.startRouteLatitude)))) AS distanceBetweenStartPoints,\n" +
                "    p.distance_unit\n" +
                "    * DEGREES(ACOS(COS(RADIANS(p.endlatpoint))\n" +
                "    * COS(RADIANS(r.finishRouteLatitude))\n" +
                "    * COS(RADIANS(p.endlongpoint - r.finishRouteLongitude))\n" +
                "    + SIN(RADIANS(p.endlatpoint))\n" +
                "    * SIN(RADIANS(r.finishRouteLatitude)))) AS distanceBetweenFinishPoints\n" +
                "  FROM routes AS r\n" +
                "    JOIN (SELECT\n" +
//                "        50.4574433 AS startlatpoint,\n" +
                "        :startlatpoint AS startlatpoint,\n" +
                "        :startlongpoint AS startlongpoint,\n" +
                "        :endlatpoint AS endlatpoint,\n" +
                "        :endlongpoint AS endlongpoint,\n" +
                "        :radius AS radius,\n" +
                "        :distance_unit AS distance_unit) AS p\n" +
                "      ON 1 = 1\n" +
                "  WHERE r.startRouteLatitude BETWEEN p.startlatpoint - (p.radius / p.distance_unit) AND p.startlatpoint + (p.radius / p.distance_unit)\n" +
                "  AND r.startRouteLongitude BETWEEN p.startlongpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint)))) AND p.startlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.startlatpoint))))\n" +
                "\n" +
                "  AND r.finishRouteLatitude BETWEEN p.endlatpoint - (p.radius / p.distance_unit) AND p.endlatpoint + (p.radius / p.distance_unit)\n" +
                "  AND r.finishRouteLongitude BETWEEN p.endlongpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint)))) AND p.endlongpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.endlatpoint))))) AS d\n" +
                "WHERE distanceBetweenStartPoints <= radius AND d.distanceBetweenFinishPoints <= d.radius\n" +
                "ORDER BY distanceBetweenStartPoints\n" +
                "LIMIT 15", "nativeSqlResultRoute")
                .setParameter("startlatpoint", passengerSearchRouteDto.getStartRouteMarkerLatitude())
                .setParameter("startlongpoint", passengerSearchRouteDto.getStartRouteMarkerLongitude())
                .setParameter("endlatpoint", passengerSearchRouteDto.getEndRouteMarkerLatitude())
                .setParameter("endlongpoint", passengerSearchRouteDto.getEndRouteMarkerLongitude())
                .setParameter("radius", 1.0)
                .setParameter("distance_unit", 111.045)
                .getResultList();
        //.list();

        //List<Route> routes = currentSession().createQuery("select distinct r from Route r inner join fetch r.routePoints ", Route.class).list();
//        currentSession().createNativeQuery("SELECT r.route_id, r.finishRouteLatitude, r.finishRouteLongitude, r.startRouteLatitude, r.startRouteLongitude, r.userId, :username FROM routes r ").setParameter("username", "123213123123").list();
        System.out.println(routes);

    }


}






