package br.com.farias.rest_com_springboot_e_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farias.rest_com_springboot_e_java.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}