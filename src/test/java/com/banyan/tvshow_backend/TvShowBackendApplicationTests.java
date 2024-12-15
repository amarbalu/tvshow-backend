package com.banyan.tvshow_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.banyan.tvshow_backend.client.TvMazeClient;
import com.banyan.tvshow_backend.service.TvShowService;

@SpringBootTest(properties = "spring.profiles.active=test")
class TvShowBackendApplicationTests {
	 @MockBean
    private TvShowService tvShowService; // Mock TvShowService to avoid actual execution

    @MockBean
    private TvMazeClient tvMazeClient; // Mock Feign Client to avoid real API calls

    @Test
    void contextLoads() {
        // Test to verify the context loads
    }
}

