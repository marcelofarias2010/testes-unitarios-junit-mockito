package br.com.farias.rest_com_springboot_e_java.repositories;

import br.com.farias.rest_com_springboot_e_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
