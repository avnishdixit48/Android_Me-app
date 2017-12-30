package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avnish on 27-12-2017.
 */
// this extends Fragment is impo bcoz it tells android how to treat this class, and its lifecycle events and creation, as a fragment.
    // now every fragment will implement a couple of methods. The firts is an empty constructor and second is onCreateView  which is called when
    //the fragmennt that we just created is inflated for display, similar to onCreate for an activity.
    // So make sure to implement both these methods for any fragment you create.

public class BodyPartFragment extends Fragment {
//
    private final String Tag="BodyPartFragment";
    private final String IMAGE_ID_LIST="";
    private final String LIST_INDEX="list_index";


    // imageResourceIDs;
    private List<Integer> mImageIds;
    public int mListIndex;
    public BodyPartFragment(){

    }
    //inflate the fragment layout and sets any image resources

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
  // load the saved state(the list of images and list index) if there is one
        if (savedInstanceState!=null){
            mImageIds=savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex=savedInstanceState.getInt(LIST_INDEX);

        }
      //inflate the android-me fragment layout
       View rootView=inflater.inflate(R.layout.fragment_body_part,container,false);
       //get a reference to the imageview in the fragment layout
        final ImageView imageView=(ImageView)rootView.findViewById(R.id.body_part_image_view);
        // set the image resource to display
        // to display image at index 0
     //   imageView.setImageResource(AndroidImageAssets.getHeads().get(0));
        if (mImageIds!=null)
        {
            // set the imageview resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));


            // setting on OnClickListener on the Imageview
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // increment position as long as the index remains<=size of the image ids list
                    if (mListIndex<mImageIds.size()-1){

                        mListIndex++;
                    }
                    else {
                        //the end of the list has been reached, so return to the beginning of the index
                        mListIndex = 0;
                    }// set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }

        else
        { // logs a message that imageid list is empty
            Log.v(Tag,"This fragment has a null list of image ID's");
        }
// return the rootview
        return rootView;
    }// Create another setter method and variable to track and set the index of the list item to display
        // ex. index = 0 is the first image id in the given list , index 1 is the second, and so on
    public void setmImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setmListIndex(int listIndex) {
        mListIndex = listIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState){
        currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }

}
