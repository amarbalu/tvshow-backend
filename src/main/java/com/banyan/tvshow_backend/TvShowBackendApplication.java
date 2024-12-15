package com.banyan.tvshow_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;

import com.banyan.tvshow_backend.repository.TvShowRepository;
import com.banyan.tvshow_backend.service.TvShowService;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
@EnableFeignClients(basePackages = "com.banyan.tvshow_backend.client")
public class TvShowBackendApplication implements CommandLineRunner {
	private final TvShowService tvShowService;
    @Autowired
    private TvShowRepository tvShowRepository;
    @Autowired
    public TvShowBackendApplication(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }
	public static void main(String[] args) {
		SpringApplication.run(TvShowBackendApplication.class, args);
	}
     @PostConstruct
    public void clearDatabase() {
        System.out.println("Clearing database on startup...");
        tvShowRepository.deleteAll();
        System.out.println("Database cleared.");
    }
	 @Override
	 @Profile("!test") // Exclude this logic during tests
    public void run(String... args) throws Exception {
    if (tvShowRepository.count() == 0) {
            System.out.println("Starting data fetching...");
            tvShowService.loadTitlesFromFile();
            System.out.println("Data fetching completed.");
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
       
    }


}
