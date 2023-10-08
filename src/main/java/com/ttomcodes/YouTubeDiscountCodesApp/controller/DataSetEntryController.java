package com.ttomcodes.YouTubeDiscountCodesApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.repackaged.com.google.common.base.Optional;
import com.google.api.services.youtube.model.VideoCategory;
import com.ttomcodes.YouTubeDiscountCodesApp.model.DataSetEntry;
import com.ttomcodes.YouTubeDiscountCodesApp.service.DataSetEntryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/data-set-entry")
@RequiredArgsConstructor
public class DataSetEntryController {
    
    private final DataSetEntryService service;
    
    @GetMapping(path = "/import-most-popular")
    public List<DataSetEntry> importMostPopularVideosByCountryCode(@RequestParam int number, @RequestParam String countryCode, @RequestParam Optional<Integer> categoryId){
        return service.importMostPopularVideosByCountryCodeAndCategoryId(number, countryCode, categoryId);
    }
    
    @GetMapping(path = "/categories")
    public List<VideoCategory> getCategoriesForCountryCode(@RequestParam String countryCode){
        return service.listCategoriesByContryCode(countryCode);
    }
}
