/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.app.Fragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {
    // create a layout file that displays one body part image named fragment_body_part, this layout contains a single ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);


        // only create new fragments when there is no previously saved instance


        if (savedInstanceState==null) {
            Intent intent=new Intent();
            Bundle extras=getIntent().getExtras();
            // Create a new BodyPartFragment instance and display it using the FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();
            // set the  list image id's for the headfragment and set the position to the send image in the list
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(extras.getInt("headIndex"));
       //     System.out.print("HIIIIII");
         //   headFragment.setmListIndex(1);
// use a fragmentmanager and transaction to add the fragment to the  screen

            //Add the fragment to its container using a Fragment annd TRansaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Fragment transaction
            fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();
// display the body and leg bodyPartFRagments

            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyPartFragment.setmListIndex(extras.getInt("bodyIndex"));
       //     bodyPartFragment.setmListIndex(1);
            fragmentManager.beginTransaction().add(R.id.bodyContainer, bodyPartFragment).commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setmImageIds(AndroidImageAssets.getLegs());
            legFragment.setmListIndex(extras.getInt("legIndex"));
        //    legFragment.setmListIndex(1);
            fragmentManager.beginTransaction().add(R.id.legContainer, legFragment).commit();



        }
    }
}
