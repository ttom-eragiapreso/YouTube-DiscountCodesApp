package com.ttomcodes.YouTubeDiscountCodesApp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttomcodes.YouTubeDiscountCodesApp.model.DataSetEntry;

public interface DataSetEntryRepository extends JpaRepository<DataSetEntry, UUID>{

}
