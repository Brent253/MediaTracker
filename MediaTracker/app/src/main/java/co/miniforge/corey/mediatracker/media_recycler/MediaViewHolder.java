package co.miniforge.corey.mediatracker.media_recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.miniforge.corey.mediatracker.MediaItemDetailActivity;
import co.miniforge.corey.mediatracker.MyListActivity;
import co.miniforge.corey.mediatracker.R;
import co.miniforge.corey.mediatracker.model.MediaItem;

/**
 * Created by corey on 10/15/17.
 */

public class MediaViewHolder extends RecyclerView.ViewHolder {
    TextView mediaName;
    TextView mediaDescription;

    View inflated;

    Context context;

    public MediaViewHolder(View itemView) {
        super(itemView);

        locateViews(itemView);
    }

    private void locateViews(View itemView) {
        inflated = itemView;
        context = itemView.getContext();

        mediaName = itemView.findViewById(R.id.mediaName);
        mediaDescription = itemView.findViewById(R.id.mediaDescription);
    }

    //    mediaItem.toJson().toString() && context.startActivity);
    public void bindData(final MediaItem mediaItem){
        this.mediaName.setText(mediaItem.title);
        this.mediaDescription.setText(mediaItem.description);

        inflated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Create a new activity with this object's data
                Intent intent = new Intent(context, MediaItemDetailActivity.class);
                intent.putExtra("mediaExtra",mediaItem.toJson().toString());

                context.startActivity(intent);

            }
        });

        //Long click listener function

        //The function returns a boolean to indicate whether you have handled
        // the click event or not. If you have not, then it will pass the event to other
        // listeners (there are none in this case, so the functionality doesn’t change)
        inflated.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
            //cast the context that is stored to MyListActivity,
                ((MyListActivity)context).deleteMediaItem(mediaItem);
                //change return value to true
                return true;
            }
        });
    }
}
