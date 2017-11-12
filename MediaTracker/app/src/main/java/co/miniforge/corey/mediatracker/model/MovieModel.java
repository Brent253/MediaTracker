package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 * Class has been Renamed from MovieItem to MovieModel
 * Added the fields to the MovieModel class: myRating(int), genre(string)
 */

public class MovieModel extends MediaItem {
    //Fields
    public int myRating;
    public String genre;

    public MovieModel(JSONObject jsonObject) {
        super(jsonObject);
    }
}
