package pl.lodz.p.it.uci;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.lodz.p.it.uci.client.Client;
import pl.lodz.p.it.uci.wsdl.VerifySignatureResponse;

@SpringBootApplication
public class UciApplication {

	public static void main(String[] args) {
		SpringApplication.run(UciApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(Client client) {
		return args -> {
			byte[] document = "base64".getBytes();
			VerifySignatureResponse response = client.verify(document);
			System.out.println("OUTPUT VALUE: " + response.getVerifySignatureReturn());
		};
	}
}
