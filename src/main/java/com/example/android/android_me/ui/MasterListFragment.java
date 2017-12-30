package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by avnish on 28-12-2017.
 */

public class MasterListFragment extends Fragment {

//Todo Define a new Interface onImageCLickListener that triggers a call back in the host activity



    OnImageClickListener mCallBack;
    // this interface calls the method in the host Activity named onImageSelected
    public interface OnImageClickListener{
        void onImageSelected(int position);

    }
    //Todo Override on Attach to make sure that the container activity has implemented the callback


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // this is to make sure that host activity has implemented the callback interface
        //if not it throws an excetion
        try{
            mCallBack=(OnImageClickListener)context;
        }
        catch (ClassCastException e){
            e.printStackTrace();
            throw new ClassCastException(context.toString()+"must implement onImageCLickListner");
        }
    }
// mandatory empty constructor
    public MasterListFragment() {
    }

    @Nullable
    @Override
    // inflates the gridview of all the AndroidMe images
        public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final  View rootView =inflater.inflate(R.layout.fragment_master_list,container,false);
        // get a referance to the GridView in the fragment_master_list xml layout
        GridView gridView=(GridView) rootView.findViewById(R.id.images_grid_view);
        // this adapter takes in the context and an arraylist of all the images resources to display
        MasterListAdapter mAdapter=new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(mAdapter);
        //Todo set a click listener on gridview and trigger the callback onImageSelected when an item is clicked
        // set a click a listener on the gridview and trigger the callback onImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // triggered the callback method and pass in the position the position that was clicked
                mCallBack.onImageSelected(position);
            }
        });


        return rootView ;
    }
}
