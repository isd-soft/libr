package com.isd.libr;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.web.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.transaction.Transactional;
import java.util.List;

@EnableScheduling
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LibrApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrApplication.class, args);



	}

//	@Bean
//	@Transactional
//	public CommandLineRunner commandLineRunner(BookRepository bookRepository){
//		return args -> {
//			Book b = new Book();
//			b.setTitle("BB");
//			b.setIndustryIdentifiers(List.of("asdas", "sdfs", "sdf", "Sdfsdf"));
//			bookRepository.save(b);
////			System.out.println(bookRepository.findAllByTitle("BB").get(0).getIndustryIdentifiers());
//		};
//	}
}
