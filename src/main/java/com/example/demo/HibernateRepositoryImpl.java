package com.example.demo;

import com.example.demo.model.Education;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class HibernateRepositoryImpl implements RepositoryPort {

    private final SessionFactory sessionFactory;


    public HibernateRepositoryImpl() {
        this.sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Education.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
    }











    @Override
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class); // Запрос на получение всех записей
            return query.list();
        }
    }


    @Override
    public Optional<Employee> findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee e LEFT JOIN FETCH e.education LEFT JOIN FETCH e.project WHERE e.id = :id";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    @Override
    public Employee save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction(); // Начинаем транзакцию
            session.merge(employee); // Сохраняем или обновляем запись
            transaction.commit(); // Завершаем транзакцию
            return employee;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("    Delete     " );
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id); // Находим запись по ID
            if (employee != null) {
                session.remove(employee); // Удаляем запись
            }
            session.getTransaction().commit();
        }
    }
}
