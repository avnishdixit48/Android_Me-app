package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by avnish on 28-12-2017.
 */
// This activity is responsible for displaying the master list of all images
    // Todo Implement the MasterListFragment callback, onImageClickListner
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

   private    int headIndex;
   private    int bodyIndex;
   private    int legIndex;
  boolean mTwopane;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout)!=null){
            mTwopane=true;
            Button button=(Button)findViewById(R.id.next_Button);
            button.setVisibility(View.GONE);

            GridView gridView=(GridView)findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);
            if (savedInstanceState==null) {
                // Create a new BodyPartFragment instance and display it using the FragmentManager
                BodyPartFragment headFragment = new BodyPartFragment();
                // set the  list image id's for the headfragment and set the position to the send image in the list
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setmListIndex(1);
// use a fragmentmanager and transaction to add the fragment to the  screen

                //Add the fragment to its container using a Fragment annd TRansaction
                FragmentManager fragmentManager = getSupportFragmentManager();
                //Fragment transaction
                fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();
// display the body and leg bodyPartFRagments

                BodyPartFragment bodyPartFragment = new BodyPartFragment();

                bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                bodyPartFragment.setmListIndex(1);
                fragmentManager.beginTransaction().add(R.id.bodyContainer, bodyPartFragment).commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(AndroidImageAssets.getLegs());
                legFragment.setmListIndex(1);
                fragmentManager.beginTransaction().add(R.id.legContainer, legFragment).commit();





            }
        }
        else {
            mTwopane=false;
        }



    }

  //define the behavior for onImageSelected
    public void onImageSelected(int position) {
        //create a toast that displays the position that was clicked

        Toast.makeText(this, "Position Clicked ="+position,Toast.LENGTH_SHORT).show();
//Todo Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        // bodyPartNumber will be 0 for head fragment, 1 for body fragment, and 2 for leg fragment
        // dividing by 12 gives us these integer values 0-11
        int bodyPartNumber=position/12;

        // this line ensures that index will allways be a value betwenn 0-11
      int listIndex=position-12*bodyPartNumber;

      if(mTwopane){

          //handle two pane case
          BodyPartFragment newFragment=new BodyPartFragment();

          // set a correctly displayed item for the correct body part fragment

          switch (bodyPartNumber) {
              case 0:

                  newFragment.setmImageIds(AndroidImageAssets.getHeads());
                  newFragment.setmListIndex(listIndex);
               //   headIndex = listIndex;
                  getSupportFragmentManager().beginTransaction().replace(R.id.head_container,newFragment);

                  break;
              case 1:
                  newFragment.setmImageIds(AndroidImageAssets.getBodies());
                  newFragment.setmListIndex(listIndex);
                  //   headIndex = listIndex;
                  getSupportFragmentManager().beginTransaction().replace(R.id.bodyContainer,newFragment);

               //   bodyIndex = listIndex;
                  break;
              case 2:
                  newFragment.setmImageIds(AndroidImageAssets.getLegs());
                  newFragment.setmListIndex(listIndex);
                 // legIndex = listIndex;
                  break;
              default:
                  break;
          }


          }

      else{
        // set a correctly displayed item for the correct body part fragment

        switch (bodyPartNumber) {
            case 0:
                headIndex = listIndex;
                break;
            case 1:
                bodyIndex = listIndex;
                break;
            case 2:
                legIndex = listIndex;
                break;
            default:
                break;
        }
        }
        // TODO (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
     // bundle is set of key values pairs
        Bundle bundle=new Bundle();
        bundle.putInt("headIndex",headIndex);
        bundle.putInt("bodyIndex",bodyIndex);
        bundle.putInt("legIndex",legIndex);

        final Intent intent=new Intent(this,AndroidMeActivity.class);
        intent.putExtras(bundle);

        Button button=(Button)findViewById(R.id.next_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
