package com.ttomcodes.YouTubeDiscountCodesApp.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataSetEntry {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean hasDiscountCode;
    private String discountCode;
    private boolean requiresCustomLink;
    private String customLink;
    
    private String videoId;
}
