package com.ttomcodes.YouTubeDiscountCodesApp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.google.api.services.youtube.model.Video;
import com.ttomcodes.YouTubeDiscountCodesApp.model.DataSetEntry;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DataEntrySetMapper {
    @Mapping(target = "videoId", source = "id")
    @Mapping(target = "description", source = "snippet.description")
    @Mapping(target = "customLink", ignore = true)
    @Mapping(target = "discountCode", ignore = true)
    @Mapping(target = "hasDiscountCode", ignore = true)
    @Mapping(target = "requiresCustomLink", ignore = true)
    @Mapping(target = "id", ignore = true)
    DataSetEntry map(Video video);
    
    List<DataSetEntry> map(List<Video> videos);
}
