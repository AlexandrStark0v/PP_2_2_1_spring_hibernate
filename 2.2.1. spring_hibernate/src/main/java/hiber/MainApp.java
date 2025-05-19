package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      Session session = context.getBean(SessionFactory.class).openSession();
//      session.beginTransaction();
//
//      session.createQuery("delete from User").executeUpdate();
//      session.createQuery("delete from Car").executeUpdate();
//
//      session.getTransaction().commit();


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 1)));
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("AUDI", 7)));
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Mazda", 5)));

      User user = userService.getUserByCar("BMW", 1);
      System.out.println("Найден пользователь: " + user);

      context.close();


   }

}
