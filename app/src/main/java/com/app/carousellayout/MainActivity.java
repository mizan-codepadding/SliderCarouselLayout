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