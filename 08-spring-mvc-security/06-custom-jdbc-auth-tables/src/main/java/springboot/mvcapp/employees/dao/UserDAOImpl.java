package springboot.mvcapp.employees.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.mvcapp.employees.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = em.createQuery("from User where userName=:uName and enabled=true", User.class);
        query.setParameter("uName", userName);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch(Exception e){
            user = null;
        }

        return user;
    }
}
