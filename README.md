# SliderCarouselLayout 
 Slider Carousel Layout . New Style Slider
 

![Screenshot_2020-03-27-11-08-32-831_com app carousellayout](https://user-images.githubusercontent.com/52482864/77724030-1e6bd500-701c-11ea-87ee-be7187a2d10e.jpg)"
  




  
In file build.gradle (Module: app) :  
```groovy  
dependencies {  
    implementation "androidx.cardview:cardview:1.0.0"
    implementation "com.github.moondroid.coverflow:library:1.0" 
}  
```  
  
   

  
#### * Using in activity_main.xml: 

```java 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:coverflow="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow
        android:id="@+id/coverflow"
        coverflow:coverHeight="150dp"
        coverflow:coverWidth="100dp"
        coverflow:maxScaleFactor="1.5"
        coverflow:reflectionGap="0px"
        coverflow:rotationThreshold="0.5"
        coverflow:scalingThreshold="0.5"
        coverflow:spacing="0.6"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</RelativeLayout>

```  
  
  
  
  
* Make a Adapter CoverFlowAdapter.java and Paste this Code:  
```java  
package com.app.carousellayout;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Game> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Game> objects) {
        this.activity = context;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Game getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.gameImage.setImageResource(data.get(position).getImageSource());
        viewHolder.gameName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Toast.makeText(activity, ""+position, Toast.LENGTH_SHORT).show();
            }
        };
    }


    private static class ViewHolder {
        private TextView gameName;
        private ImageView gameImage;

        public ViewHolder(View v) {
            gameImage = (ImageView) v.findViewById(R.id.image);
            gameName = (TextView) v.findViewById(R.id.name);
        }
    }
}
```







####  *  MainActivity.java : 
```java  
  
package com.app.carousellayout;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        coverFlow.setReflectionOpacity(0);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        games = new ArrayList<>();
        games.add(new Game(R.drawable.assassins_creed, "Assassin Creed 3"));
        games.add(new Game(R.drawable.avatar_3d, "Avatar 3D"));
        games.add(new Game(R.drawable.call_of_duty_black_ops_3, "Call Of Duty Black Ops 3"));
        games.add(new Game(R.drawable.dota_2, "DotA 2"));
        games.add(new Game(R.drawable.halo_5, "Halo 5"));
        games.add(new Game(R.drawable.left_4_dead_2, "Left 4 Dead 2"));
        games.add(new Game(R.drawable.starcraft, "StarCraft"));
        games.add(new Game(R.drawable.the_witcher_3, "The Witcher 3"));
        games.add(new Game(R.drawable.tomb_raider, "Tom raider 3"));
        games.add(new Game(R.drawable.need_for_speed_most_wanted, "Need for Speed Most Wanted"));
    }
}

```



### item_flow_view.xml
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="140dp"
android:layout_height="180dp">

<ImageView
    android:id="@+id/image"
    android:contentDescription="@string/app_name"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop" />

<TextView
    android:id="@+id/name"
    android:layout_gravity="bottom"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center"
    android:textAppearance="?android:attr/textAppearanceSmallInverse" />

</FrameLayout>
```


### Make a Model  Game
```
package com.app.carousellayout;

public class Game {

    private String name;
    private int imageSource;

    public Game (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}

```

### Reporting issues or improvements  🛠

Found a bug or a problem on a specific feature? Open an issue on  [Github issues](https://github.com/DavidBarbaran/FCM-AndroidToOtherDevice/issues)
