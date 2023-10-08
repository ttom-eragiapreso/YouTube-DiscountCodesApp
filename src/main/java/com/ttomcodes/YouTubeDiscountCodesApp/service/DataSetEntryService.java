package com.ttomcodes.YouTubeDiscountCodesApp.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.repackaged.com.google.common.base.Optional;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoCategory;
import com.google.api.services.youtube.model.VideoListResponse;
import com.ttomcodes.YouTubeDiscountCodesApp.mapper.DataEntrySetMapper;
import com.ttomcodes.YouTubeDiscountCodesApp.model.DataSetEntry;
import com.ttomcodes.YouTubeDiscountCodesApp.repository.DataSetEntryRepository;
import com.ttomcodes.YouTubeDiscountCodesApp.utils.YouTubeUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataSetEntryService {
    
    private YouTubeService youTubeService = new YouTubeService();
    
    private YouTube youtube = youTubeService.createYouTubeService();
    
    private final DataSetEntryRepository dataSetEntryRepository;
    
    private final DataEntrySetMapper mapper;
    
    public List<DataSetEntry> importMostPopularVideosByCountryCodeAndCategoryId(int number, String countryCode, Optional<Integer> categoryId){
        
        YouTube.Videos.List request;
        try {
            request = youtube.videos().list("snippet,statistics");
            request.setKey(YouTubeUtils.API_KEY);
            request.setMaxResults(Integer.toUnsignedLong(number));
            request.setRegionCode(countryCode);
            request.setChart("mostPopular");
            if(categoryId.isPresent()) {
                request.setVideoCategoryId(categoryId.get().toString());
            }
            VideoListResponse response = request.execute();
            List<Video> items = response.getItems();
            return dataSetEntryRepository.saveAll(mapper.map(items));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

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
