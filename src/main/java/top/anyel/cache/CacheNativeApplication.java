package top.anyel.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheNativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheNativeApplication.class, args);
	}

}
