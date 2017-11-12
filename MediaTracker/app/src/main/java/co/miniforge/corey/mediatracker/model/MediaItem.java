package co.miniforge.corey.mediatracker.model;

import android.util.Log;

import org.json.JSONObject;

import java.security.MessageDigest;

import co.miniforge.corey.mediatracker.media_store.Md5IdHelper;

/**
 * Created by corey on 10/20/17.
 */

public class MediaItem {
    //Remove the public static defaultId field
 //   public static int defaultId = 0;

    public String id;
    public String title;
    public String description;
    public String url;

    //We need a field that stores what kind of media item this is
    //We will do this by creating an enum that can identify the type of item
    public MediaItemType type = MediaItemType.Generic;

    public MediaItem(JSONObject jsonObject){
        try{
            //Generate id based on the object instance (should work :D)
            this.id = jsonObject.getString("id");
            this.title = jsonObject.getString("title");
            this.description = jsonObject.getString("description");
            this.url = jsonObject.getString("url");
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }
    }

    public MediaItem(){
        //this.id = Md5IdHelper.idForObject(defaultId++);
       // Change it to: this.id = Md5IdHelper.idForObject(this);

//        This is actually a fix for a bug that was found.
//                In some cases the Id was the exact same on multiple items,
//                so that if you restarted the app, edited an item and saved it,
//                it would update in multiple areas

        this.id = Md5IdHelper.idForObject(this);
        this.title = "defaultTitle";
        this.description = "defaultDescription";
        this.url = "defaultUrl";
    }

    //getTypeForString will pull the type value from the JSON object.
    MediaItemType getTypeForString(String value){
        switch (value){
            case "TV":
                return  MediaItemType.TV;
            case "Movie":
                return  MediaItemType.Movie;
            default:
                return MediaItemType.Generic;
        }
    }

    // getStringForType will be used to save the type into a json object more efficiently.
    String getStringForType (MediaItemType type){
        switch (type) {
            case Movie:
                return "Movie";
            case TV:
                return "TV";
            default:
                return "Generic";
        }
    }

    public JSONObject toJson(){
        JSONObject mediaItem = new JSONObject();

        try{
            mediaItem.put("id", this.id);
            mediaItem.put("title", this.title);
            mediaItem.put("description", this.description);
            mediaItem.put("url", this.url);
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }

        return mediaItem;
    }
}
