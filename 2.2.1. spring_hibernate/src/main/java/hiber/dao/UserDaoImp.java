package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<User> listUsers() {
      Session session = sessionFactory.getCurrentSession();
      return session.createQuery(
              "SELECT u FROM User u JOIN FETCH u.car", User.class
      ).getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      return session.createQuery(
                      "SELECT u FROM User u JOIN FETCH u.car WHERE u.car.model = :model AND u.car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getSingleResult();
   }

}
