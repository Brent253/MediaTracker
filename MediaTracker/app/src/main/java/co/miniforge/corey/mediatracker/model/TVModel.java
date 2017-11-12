package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 * Class has been renamed from TVItem to TVModel
 */

public class TVModel extends MediaItem {
    public TVModel(JSONObject jsonObject) {
        super(jsonObject);
    }
}
