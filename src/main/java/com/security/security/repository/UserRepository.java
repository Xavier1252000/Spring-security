package com.security.security.repository;

import com.security.security.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByUsernameOrEmail(String username){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        Predicate usernamePredicate = criteriaBuilder.equal(userRoot.get("username"), username);
        Predicate emailIdPredicate = criteriaBuilder.equal(userRoot.get("emailId"), username);
        Predicate orPredicate = criteriaBuilder.or(emailIdPredicate, usernamePredicate);
        query.where(orPredicate);
        TypedQuery<User> userQuery = entityManager.createQuery(query);
        User user;
        try {
            user=userQuery.getSingleResult();
        }catch (NoResultException e){
            user=null;
        }
        return user;
    }

    @Transactional
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public List<User> getAllUsers() {
        String jpql = "SELECT u FROM users u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }
}
