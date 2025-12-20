package springboot.mvcapp.employees.dao;

import springboot.mvcapp.employees.entity.Role;

public interface RoleDAO {
    Role findRoleByName(String role);
}
