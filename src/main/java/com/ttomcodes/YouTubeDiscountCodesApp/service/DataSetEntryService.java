package com.ttomcodes.YouTubeDiscountCodesApp.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoCategory;
import com.ttomcodes.YouTubeDiscountCodesApp.model.DataSetEntry;
import com.ttomcodes.YouTubeDiscountCodesApp.utils.YouTubeUtils;

@Service
public class DataSetEntryService {
    
    //@Autowired
    private YouTubeService youTubeService = new YouTubeService();
    
    private YouTube youtube = youTubeService.createYouTubeService();
    
    public List<DataSetEntry> importVideos(int number){
        
        
        return null;
    }
    
    public List<VideoCategory> listCategoriesByContryCode(String countryCode) {
        try {
            return youtube.videoCategories()
                    .list("snippet")
                    .setRegionCode(countryCode)
                    .setKey(YouTubeUtils.API_KEY)
                    .execute()
                    .getItems();
            
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
            return Collections.emptyList();
        }
    }
    
    
}
