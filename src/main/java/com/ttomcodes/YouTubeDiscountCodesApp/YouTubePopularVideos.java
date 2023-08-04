package com.ttomcodes.YouTubeDiscountCodesApp;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YouTubePopularVideos {

    private static final String API_KEY = "AIzaSyDbELR3qgB3xgRfBphKHWXsOBUCfceiMV0";
    private static final String APPLICATION_NAME = "YouTube API Example";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Error initializing HTTP transport.", e);
        }
    }

    public static void main(String[] args) {
        try {
            YouTube youtubeService = createYouTubeService(API_KEY);
            List<String> popularVideoIds = getPopularVideoIds(youtubeService, "IT");
            getVideoDetails(youtubeService, popularVideoIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static YouTube createYouTubeService(String apiKey) throws IOException {
        HttpRequestInitializer requestInitializer = request -> {
            request.getHeaders().set("Referer", "http://localhost/");
        };
        return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static List<String> getPopularVideoIds(YouTube youtube, String regionCode) throws IOException {
        // devo usare la youtube videos list invece che youtube search list
        YouTube.Search.List request = youtube.search().list("id");
        request.setRelevanceLanguage(regionCode);
        request.setKey(API_KEY);
        request.setType("video");
        request.setRegionCode(regionCode);
        request.setOrder("date");
        request.setMaxResults(20L); // You can adjust the number of popular videos to retrieve
        SearchListResponse response = request.execute();
        List<SearchResult> items = response.getItems();

        System.out.println("Popular Video IDs in " + regionCode + ":");
        for (SearchResult item : items) {
            ResourceId rId = item.getId();
            System.out.println(rId.getVideoId());
        }

        return response.getItems().stream()
                .map(item -> item.getId().getVideoId())
                .toList();
    }

    private static void getVideoDetails(YouTube youtube, List<String> videoIds) throws IOException {
        YouTube.Videos.List request = youtube.videos().list("snippet,statistics");
        request.setKey(API_KEY);
        request.setId(String.join(",", videoIds));
        VideoListResponse response = request.execute();
        List<Video> items = response.getItems();

        System.out.println("\nDetails of Popular Videos:");
        for (Video item : items) {
            System.out.println("Title: " + item.getSnippet().getTitle());
            System.out.println("Views: " + item.getStatistics().getViewCount());
            System.out.println("Likes: " + item.getStatistics().getLikeCount());
            System.out.println("Dislikes: " + item.getStatistics().getDislikeCount());
            System.out.println("Description: " + item.getSnippet().getDescription());
            System.out.println("Published At: " + item.getSnippet().getPublishedAt());
            System.out.println("--------------");
        }
    }
}
