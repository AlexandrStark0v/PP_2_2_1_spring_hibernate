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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      try { return (User) sessionFactory.getCurrentSession()
                 .createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series")
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .uniqueResult();
      } catch (org.hibernate.NonUniqueResultException e) {
         System.out.println("Найдено больше одного пользователя с данной машиной");
         return null;
      } catch (Exception e) {
         System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
         return null;
      }
   }

}
