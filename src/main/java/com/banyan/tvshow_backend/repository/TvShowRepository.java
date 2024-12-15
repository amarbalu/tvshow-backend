package com.banyan.tvshow_backend.repository;

import com.banyan.tvshow_backend.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {}

