package com.banyan.tvshow_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Database-generated ID

    private String name;
    @Lob
    private String summary;
    private String language;

    @Lob
    private String genres; // Stored as a comma-separated string

    private String status;

    private String network;
    private String webChannel;
    private String officialSite;

    @Lob
    private String imageMediumUrl;
    @Lob
    private String imageOriginalUrl;

    private String url;
    private String type;
    private Integer runtime;
    private Integer averageRuntime;
    private String premiered;
    private String ended;

    @Lob
    private String schedule; // Stored as JSON string

    private Double rating;
    private Integer weight;



    private String externalImdb;
    private String externalTvRage;
    private String externalTheTvDb;

    private Long lastUpdated;

    @Lob
    private String links; // Stored as JSON string
}
