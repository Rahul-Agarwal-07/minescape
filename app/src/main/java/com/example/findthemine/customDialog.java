package com.example.findthemine;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class customDialog extends AppCompatActivity {

    LinearLayout newGame, mainMenu;

    TextView scoreTxt;

    public void show(Activity activity,int finalScore)
    {

        Dialog dialog = new Dialog(activity,R.style.PauseDialog);
        dialog.setContentView(R.layout.game_over_layout);

        newGame = dialog.findViewById(R.id.newGameBtn);
        mainMenu = dialog.findViewById(R.id.mainMenuBtn);
        scoreTxt = dialog.findViewById(R.id.finalScore);

        scoreTxt.setText("SCORE : " + finalScore);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("check","Intent Passed");
                startActivity(new Intent(activity,main_menu.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dialog.getWindow().setBackgroundBlurRadius(10);
        }

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
}
