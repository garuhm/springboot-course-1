package springboot.mvcapp.employees.dao;

import springboot.mvcapp.employees.entity.User;

public interface UserDAO {
    User findByUserName(String userName);
}
