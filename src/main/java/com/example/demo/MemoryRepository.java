package com.example.demo;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryRepository implements RepositoryPort {
    List<Employee> repository = new ArrayList();

    {
        Employee employee1 = Employee
                .builder()
                .id(1)
                .name("Petja")
                .mail("safasf@mail.ru")
                .telephon("375295624872")
                .build();
        repository.add(employee1);
        Employee employee2 = Employee
                .builder()
                .id(2)
                .name("Kolja")
                .mail("sazdgf@mail.ru")
                .telephon("375295624563472")
                .build();
        repository.add(employee2);
        Employee employee3 = Employee
                .builder()
                .id(3)
                .name("Masha")
                .mail("sazetggf@mail.ru")
                .telephon("3752954563472")
                .build();
        repository.add(employee3);
        Employee employee4 = Employee
                .builder()
                .id(4)
                .name("Dasha")
                .mail("sahgf@mail.ru")
                .telephon("3752954547782")
                .build();
        repository.add(employee4);
    }


    @Override
    public List<Employee> findAll() {
        return repository;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return repository.stream().filter(e->e.getId().equals( id)).findFirst();
    }

    @Override
    public Employee save(Employee t) {
       // repository.remove( repository.stream().filter(e->e.getId() == t.getId()).findFirst());
        repository.add(t);
        return t;
    }


    @Override
    public void deleteById(Integer id) {
        repository.remove(repository.stream().filter(e->e.getId().equals(id)).findFirst());
    }
}
