package com.example.findthemine;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.sql.Time;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {

    boardFrag bfrag = new boardFrag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View main = findViewById(R.id.main);

        int id = getResources().getIdentifier("config_showNavigationBar","bool","android");
        boolean result = id > 0 && getResources().getBoolean(id);

        if(result) {
            ViewGroup.MarginLayoutParams paramsMain = (ViewGroup.MarginLayoutParams) main.getLayoutParams();
            paramsMain.bottomMargin = 120;
        }

        loadFrag(bfrag);
    }

    public void playGame(View v) throws InterruptedException {
        Log.d("check","yes");
        bfrag.playgame(v);
    }

    public void loadFrag(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.boardFrame,fragment);
        ft.commit();
    }




}