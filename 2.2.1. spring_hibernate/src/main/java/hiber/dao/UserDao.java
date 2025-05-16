package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   @SuppressWarnings("unchecked")
   List<User> listUsers();

   User getUserByCar(String model, int series);
}
