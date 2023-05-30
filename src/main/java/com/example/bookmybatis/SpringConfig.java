package com.example.bookmybatis;


import com.example.bookmybatis.repository.BookRepository;
import com.example.bookmybatis.repository.DataJpaBookRepository;
import com.example.bookmybatis.repository.JpaBookRepository;
import com.example.bookmybatis.repository.MybatisBookRepository;
import com.example.bookmybatis.service.BookService;
import jakarta.persistence.EntityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.bookmybatis.repository")
@EnableJpaRepositories("com.example.bookmybatis.repository")
public class SpringConfig {
//    private final MybatisBookRepository bookRepository;
//    private final DataJpaBookRepository bookRepository; // DataJPA용
    private EntityManager em; // JPA용


//    public SpringConfig(MybatisBookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    public SpringConfig(DataJpaBookRepository bookRepository){
//        this.bookRepository = bookRepository;
//    }
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public BookService bookService(){
//        return new BookService(bookRepository); // myBatis와 dataJPA용
        return new BookService(bookRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        return new JpaBookRepository(em);
    }
}
