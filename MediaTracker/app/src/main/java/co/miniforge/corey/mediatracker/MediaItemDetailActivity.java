package co.miniforge.corey.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * This activity will display the contents of a media item and allow the user to update the contents
 * of the item. When the user clicks the save button, the activity should create an intent that goes
 * back to MyListActivity and puts the MediaItem into the intent (If you are stuck on that, read through
 * the code in MyListActivity)
 */
public class MediaItemDetailActivity extends AppCompatActivity {

    public static String mediaExtra = "mediaItem";

    //Variable reference to textFields
    EditText title;
    EditText description;
    EditText url;

    //Media item
    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_detail);

        locateViews();
        bindData();
        buttonListener();
    }

    void locateViews(){
        title = (EditText) findViewById(R.id.titleField);
        description = (EditText) findViewById(R.id.descriptionField);
        url = (EditText) findViewById(R.id.urlField);
    }

    void bindData()
    {
        if(getIntent().hasExtra(MyListActivity.mediaExtra))
        {
            //Get the extra and store it in a string
            String mediaItemReference = getIntent().getStringExtra(MyListActivity.mediaExtra);

            //initialize json object

            JSONObject json = null;
            //Create a json object with reference to media item
            try {
                json = new JSONObject(mediaItemReference);
            } catch (JSONException e) {

                //catch error
                Log.e("Error", "Error creating JSON object" + e.getStackTrace());
            }

            //if the object is not null
            if(json != null)
            {
                //create new mediaItem with reference to our json object
                mediaItem = new MediaItem(json);

                //for each textfield, reference the media item
                title.setText(mediaItem.title);
                description.setText(mediaItem.description);
                url.setText(mediaItem.url);
            }

            else
            {
                Log.e("Error", "Error creating mediaItem reference");
            }
        }
    }

    //Method to set onclick listener for save button
    public void buttonListener()
    {
        Button saveItems = (Button) findViewById(R.id.saveButton);

        //When the save button is clicked.
        saveItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MyListActivity.class);
                mediaItem.title = title.getText().toString();
                mediaItem.description = description.getText().toString();
                mediaItem.url = url.getText().toString();


                intent.putExtra(MyListActivity.mediaExtra,mediaItem.toJson().toString());


                startActivity(intent);
            }
        });
    }


}
