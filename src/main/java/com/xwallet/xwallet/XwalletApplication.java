package com.xwallet.xwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;
import java.security.KeyStore;

@SpringBootApplication
@EnableScheduling
public class XwalletApplication {

	public static void main(String[] args) {
		cargarPfx();
		SpringApplication.run(XwalletApplication.class, args);
	}

	private static void cargarPfx() {
		try {
			// Cargar el archivo PFX desde resources
			InputStream pfxInputStream = new ClassPathResource("certificate.pfx").getInputStream();

			// Crear un objeto KeyStore y cargar el archivo PFX
			KeyStore pfxKeyStore = KeyStore.getInstance("PKCS12");
			pfxKeyStore.load(pfxInputStream, "".toCharArray());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
