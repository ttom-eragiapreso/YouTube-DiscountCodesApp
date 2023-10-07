package com.ttomcodes.YouTubeDiscountCodesApp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private UUID id;
    
    private String description;
    private boolean hasDiscountCode;
    private String discountCode;
    private boolean requiresCustomLink;
    private String customLink;
}
