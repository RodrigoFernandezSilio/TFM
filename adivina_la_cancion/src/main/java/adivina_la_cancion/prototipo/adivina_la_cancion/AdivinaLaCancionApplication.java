package adivina_la_cancion.prototipo.adivina_la_cancion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdivinaLaCancionApplication {

	public static void main(String[] args) {
		System.out.println(SpringVersion.getVersion());
		SpringApplication.run(AdivinaLaCancionApplication.class, args);
	}

}
