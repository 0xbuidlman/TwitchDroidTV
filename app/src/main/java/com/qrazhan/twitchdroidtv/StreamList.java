package com.qrazhan.twitchdroidtv;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public final class StreamList {
    public static final String MOVIE_CATEGORY[] = {
            "Featured Streams",
            "Top Games",
            "Category Two",
            "Category Three",
            "Category Four",
            "Category Five",
    };

    public static List<Stream> list;

    public static Stream buildStreamInfo(String category, String title,
            String description, String studio, String videoUrl, String cardImageUrl,
            String bgImageUrl, boolean vertical) {
        Stream stream = new Stream();
        stream.setId(Stream.getCount());
        Stream.incCount();
        stream.setTitle(title);
        stream.setDescription(description);
        stream.setStudio(studio);
        stream.setCategory(category);
        stream.setCardImageUrl(cardImageUrl);
        stream.setBackgroundImageUrl(bgImageUrl);
        stream.setVideoUrl(videoUrl);
        stream.setVertical(vertical);
        return stream;
    }
}
