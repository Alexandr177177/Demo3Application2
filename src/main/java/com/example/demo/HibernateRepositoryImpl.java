package com.example.demo;

import com.example.demo.model.primary.Education;
import com.example.demo.model.primary.Employee;
import com.example.demo.model.primary.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Primary
public class HibernateRepositoryImpl implements RepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        String jpql = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Integer id) {
        String jpql = "SELECT e FROM Employee e LEFT JOIN FETCH e.education LEFT JOIN FETCH e.project WHERE e.id = :id";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("id", id);

        try {
            Employee employee = query.getSingleResult();
            return Optional.of(employee);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
            return employee;
        } else {
            return entityManager.merge(employee);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }


}