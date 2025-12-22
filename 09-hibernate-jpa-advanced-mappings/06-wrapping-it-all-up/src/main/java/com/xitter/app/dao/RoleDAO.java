package com.xitter.app.dao;

import com.xitter.app.entity.Role;

public interface RoleDAO {
    Role findRoleByName(String name);
}
