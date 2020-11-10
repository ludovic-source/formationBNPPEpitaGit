package coursEpitaExerciceModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "coursEpitaExerciceModule.dao")
@EntityScan("coursEpitaExerciceModule.entite")
//pour pr�ciser o� se trouvent les controller
@ComponentScan(basePackages = {"coursEpitaExerciceModule","coursEpitaExerciceModule.service"} )
public class coursEpitaExerciceModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(coursEpitaExerciceModuleApplication.class, args);
		System.out.println("�a marche !");

	}

}
