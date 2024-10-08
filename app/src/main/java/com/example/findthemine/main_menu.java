package com.example.findthemine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class main_menu extends AppCompatActivity {

    TextView titleText;

    int flag = 0;
    Button newGame, exitGame, howtoPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_menu_layout);

        View v = findViewById(R.id.menuMain);

        int id = getResources().getIdentifier("config_showNavigationBar","bool","android");
        boolean res = id > 0 && getResources().getBoolean(id);

        if(res)
        {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.bottomMargin = 120;
        }

        init();

        YoYo.with(Techniques.Bounce).repeat(YoYo.INFINITE).playOn(titleText);



    }

    public void onClick(View v)
    {
        if(v.getId() == newGame.getId())
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        if(v.getId() == exitGame.getId())
        {
            finishAndRemoveTask();
        }

        if(v.getId() == howtoPlay.getId())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    setContentView(R.layout.howtoplay_dialog);
                    flag = 1;
                }
            },500);
        }
    }


    public void init()
    {
        titleText = findViewById(R.id.title_text);
        newGame = findViewById(R.id.newGame);
        exitGame = findViewById(R.id.exitGame);
        howtoPlay = findViewById(R.id.howtoPlay);
    }

    @Override
    public void onBackPressed() {
        if(flag == 1) startActivity(new Intent(this, main_menu.class));
        flag = 0;
        super.onBackPressed();

    }
}
