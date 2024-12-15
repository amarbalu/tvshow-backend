package com.banyan.tvshow_backend.client;

import com.banyan.tvshow_backend.dto.TvShowDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tvMazeClient", url = "http://api.tvmaze.com")
public interface TvMazeClient {

    @GetMapping("/singlesearch/shows")
    TvShowDetails getTvShow(@RequestParam("q") String title);

    // Fetch full details by show ID
    @GetMapping("/shows/{id}")
    TvShowDetails getTvShowById(@RequestParam("id") Long id);
}
