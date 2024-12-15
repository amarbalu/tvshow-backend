package com.banyan.tvshow_backend.service;

import com.banyan.tvshow_backend.client.TvMazeClient;
import com.banyan.tvshow_backend.dto.TvShowDetails;
import com.banyan.tvshow_backend.model.TvShow;
import com.banyan.tvshow_backend.repository.TvShowRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class TvShowService {
    private final TvMazeClient tvMazeClient;
    private final TvShowRepository tvShowRepository;
    @Value("${app.filepath}")
    private String filePath;
    
    @Autowired
    public TvShowService(TvMazeClient tvMazeClient, TvShowRepository tvShowRepository) {
        this.tvMazeClient = tvMazeClient;
        this.tvShowRepository = tvShowRepository;
    }

    public void loadTitlesFromFile() {
        try {
                System.out.println("Attempting to read file: " + filePath);

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }
                Files.lines( Path.of(filePath)).forEach(this::processTitle);
            
        } catch (IOException e) {
            System.err.println("Error loading titles: " + e.getMessage());
        }
    }

    // Helper method to sanitize titles
private String sanitizeTitle(String title) {
    // List of delimiters to truncate before
    String[] delimiters = {":", "-", "("};

    // Find the first occurrence of any delimiter
    int truncateIndex = -1;
    for (String delimiter : delimiters) {
        int index = title.indexOf(delimiter);
        if (index != -1 && (truncateIndex == -1 || index < truncateIndex)) {
            truncateIndex = index;
        }
    }

    // Truncate the title and replace spaces with `+`
    String truncatedTitle = (truncateIndex != -1) ? title.substring(0, truncateIndex) : title;
    return truncatedTitle.trim();
}
 private String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void processTitle(String title) {
    try {
        // Replace spaces with "+" and truncate before colon if present
        String apiTitle = sanitizeTitle(title);

        // System.out.println("Hitting API with title: " + apiTitle);

        // Fetch show details from the API
        TvShowDetails details = tvMazeClient.getTvShow(apiTitle);

        // Save the successfully fetched details
        TvShow tvShow =  new TvShow(
     null, // Database-generated ID
            details.getName() != null ? details.getName() : "Unknown Title",
            details.getSummary() != null ? details.getSummary() : "No summary available",
            details.getLanguage() != null ? details.getLanguage() : "Unknown",
            details.getGenres() != null ? String.join(",", details.getGenres()) : "Unknown Genre", // Convert list to comma-separated string
            details.getStatus() != null ? details.getStatus() : "Unknown Status",
            details.getNetwork() != null ? details.getNetwork().getName() : "Unknown Network",
            details.getWebChannel() != null ? details.getWebChannel().getName() : "Unknown Web Channel",
            details.getOfficialSite() != null ? details.getOfficialSite() : "No official site",
            details.getImage() != null && details.getImage().getMedium() != null ? details.getImage().getMedium() : "No medium image",
            details.getImage() != null && details.getImage().getOriginal() != null ? details.getImage().getOriginal() : "No original image",
            details.getUrl() != null ? details.getUrl() : "No URL",
            details.getType() != null ? details.getType() : "Unknown Type",
            details.getRuntime() != null ? details.getRuntime() : 0,
            details.getAverageRuntime() != null ? details.getAverageRuntime() : 0,
            details.getPremiered() != null ? details.getPremiered() : "Unknown Premiere Date",
            details.getEnded() != null ? details.getEnded() : "Unknown End Date",
            details.getSchedule() != null ? toJson(details.getSchedule()) : "{}",
            details.getRating() != null && details.getRating().getAverage() != null ? details.getRating().getAverage() : 0.0,
            details.getWeight() != null ? details.getWeight() : 0,
        
            details.getExternals() != null && details.getExternals().getImdb() != null ? details.getExternals().getImdb() : "No IMDb ID",
            details.getExternals() != null && details.getExternals().getTvrage() != null ? details.getExternals().getTvrage().toString() : "No TVRage ID",
            details.getExternals() != null && details.getExternals().getThetvdb() != null ? details.getExternals().getThetvdb().toString() : "No TVDB ID",
            details.getUpdated() != null ? details.getUpdated() : System.currentTimeMillis(), // Use current time if `updated` is null
            details.get_links() != null ? toJson(details.get_links()) : "{}"
);
        tvShowRepository.save(tvShow);

        // System.out.println("Successfully processed title: " + title);
        // Add a delay between requests (e.g., 500ms)
        Thread.sleep(500);
    } catch (FeignException.NotFound e) {
        // Print failing title
        System.out.println("Failed to process title: " + title);
    } catch (Exception e) {
        // Print failing title and error details
        System.out.println("Unexpected error for title: " + title);
        e.printStackTrace();
    }
}



    public List<TvShow> listAllShows() {
        return tvShowRepository.findAll();
    }

    public TvShow getShowDetails(Long id) {
        return tvShowRepository.findById(id).orElseThrow(() -> new RuntimeException("TV Show not found"));
    }
}
