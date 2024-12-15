package com.banyan.tvshow_backend.dto;

import lombok.Data;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
public class TvShowDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure IDs are auto-incrementing
    private Long id;
     private String url;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private Integer runtime; // Changed to Integer
    private Integer averageRuntime; // Changed to Integer
    private String premiered;
    private String ended;
    private String officialSite;

    private Schedule schedule;
    private Rating rating;
    private Integer weight;
    private Network network;
    private WebChannel webChannel;

    private Externals externals;
    private Image image;
    private String summary;
    private Long updated;

    private Links _links;

    @Data
    public static class Schedule {
        private String time;
        private List<String> days;
    }

    @Data
    public static class Rating {
        private Double average; // Changed to Double
    }

    @Data
    public static class Network {
        private Long id;
        private String name;
        private Country country;
    }

    @Data
    public static class WebChannel {
        private Long id;
        private String name;
        private Country country;
        private String officialSite;
    }

    @Data
    public static class Country {
        private String name;
        private String code;
        private String timezone;
    }

    @Data
    public static class Externals {
        private Long tvrage;
        private Long thetvdb;
        private String imdb;
    }

    @Data
    public static class Image {
        private String medium;
        private String original;
    }

    @Data
    public static class Links {
        private Self self;
        private PreviousEpisode previousepisode;

        @Data
        public static class Self {
            private String href;
        }

        @Data
        public static class PreviousEpisode {
            private String href;
            private String name;
        }
    }
}
