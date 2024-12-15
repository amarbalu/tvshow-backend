package com.banyan.tvshow_backend.controller;

import com.banyan.tvshow_backend.model.TvShow;
import com.banyan.tvshow_backend.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow requests from React
@RequestMapping("/api/shows")
public class TvShowController {
    private final TvShowService tvShowService;

    @Autowired
    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    public ResponseEntity<List<TvShow>> listAllShows() {
        return ResponseEntity.ok(tvShowService.listAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getShowDetails(@PathVariable Long id) {
        return ResponseEntity.ok(tvShowService.getShowDetails(id));
    }
}

