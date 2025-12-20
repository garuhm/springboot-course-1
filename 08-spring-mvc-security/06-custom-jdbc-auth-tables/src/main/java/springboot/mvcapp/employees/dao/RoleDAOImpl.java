package springboot.mvcapp.employees.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.mvcapp.employees.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO{
    private EntityManager em;

    @Autowired
    public RoleDAOImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Role findRoleByName(String roleName) {
        TypedQuery<Role> query = em.createQuery("from Role where name=:role", Role.class);
        query.setParameter("role", roleName);

        Role role = null;
        try {
            role = query.getSingleResult();
        } catch(Exception e){
            role = null;
        }

        return role;
    }
}
