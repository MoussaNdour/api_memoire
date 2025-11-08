package memoire.api.memoire_licence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class MemoireLicenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoireLicenceApplication.class, args);
	}

}
