package fr.uca.StockServiceNicolasPierre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockServiceNicolasPierreApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StockServiceNicolasPierreApplication.class, args);

		// The port that we should run on can be set into an environment variable
		// Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
	}

}
