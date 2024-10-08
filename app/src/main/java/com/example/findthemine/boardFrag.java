package com.example.findthemine;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.HashMap;
import java.util.HashSet;

import eightbitlab.com.blurview.BlurView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link boardFrag} factory method to
 * create an instance of this fragment.
 */
public class boardFrag extends Fragment {


    int rowCount, columnCount, count_0, count_1, count_2, count_3, bombCount, points;
    int flag = 0;

    TextView pointsTxt;
    ImageView gameOver;

    MediaPlayer mp;

    HashSet<String> bombXY = new HashSet<>();
    HashSet<String> xy_0 = new HashSet<>();
    HashSet<String> xy_1 = new HashSet<>();
    HashSet<String> xy_2 = new HashSet<>();
    HashSet<String> xy_3 = new HashSet<>();

    public boardFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setXY();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View View = inflater.inflate(R.layout.fragment_board, container, false);
        return View;


    }

    public void init() {
        rowCount = 6;
        columnCount = 6;
        bombCount = 6;
        count_0 = 10;
        count_1 = 9;
        count_2 = 7;
        count_3 = 4;
        points = 0;

        pointsTxt = requireActivity().findViewById(R.id.pointstxt);
        gameOver = requireActivity().findViewById(R.id.gameOverImg);
    }

    public void setXY() {
        while (xy_0.size() < count_0) {
            int x = 0 + (int) (Math.random() * ((rowCount - 1 - 0) + 1));
            int y = 0 + (int) (Math.random() * ((columnCount - 1 - 0) + 1));

            String xy = "" + x + y;

            if (!xy_0.contains(xy)) {
                xy_0.add(xy);
            }
        }

        while (xy_1.size() < count_1) {
            int x = 0 + (int) (Math.random() * ((rowCount - 1 - 0) + 1));
            int y = 0 + (int) (Math.random() * ((columnCount - 1 - 0) + 1));

            String xy = "" + x + y;

            if (!xy_1.contains(xy) && !xy_0.contains(xy)) {
                xy_1.add(xy);
            }
        }

        while (xy_2.size() < count_2) {
            int x = 0 + (int) (Math.random() * ((rowCount - 1 - 0) + 1));
            int y = 0 + (int) (Math.random() * ((columnCount - 1 - 0) + 1));

            String xy = "" + x + y;

            if (!xy_2.contains(xy) && !xy_0.contains(xy) && !xy_1.contains(xy)) {
                xy_2.add(xy);
            }
        }

        while (xy_3.size() < count_3) {
            int x = 0 + (int) (Math.random() * ((rowCount - 1 - 0) + 1));
            int y = 0 + (int) (Math.random() * ((columnCount - 1 - 0) + 1));

            String xy = "" + x + y;

            if (!xy_3.contains(xy) && !xy_0.contains(xy) && !xy_1.contains(xy) && !xy_2.contains(xy)) {
                xy_3.add(xy);
            }
        }

        while (bombXY.size() < bombCount) {
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    String tmp = "" + i + j;

                    if (!xy_0.contains(tmp) && !xy_1.contains(tmp) && !xy_2.contains(tmp) && !xy_3.contains(tmp))
                        bombXY.add(tmp);
                }
            }
        }

        return;
    }

    public void playgame(View v) throws InterruptedException {
        ImageButton btn = (ImageButton) v;
        Log.d("check","yes");
        if (flag == 1) return;

        String xy = v.getResources().getResourceName(v.getId());

        Log.d("check","yes" + xy);
        xy = xy.substring(xy.length() - 2, xy.length());

        Log.d("check","yes" + xy);

        if (xy_0.contains(xy)) {
            Log.d("check","yes");
            btn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.xy0));
        }

        if (xy_1.contains(xy)) {
            Log.d("check","yes");
            btn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.xy1));
            points += 1;
        }

        if (xy_2.contains(xy)) {
            Log.d("check","yes");
            btn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.xy2));
            points += 2;
        }

        if (xy_3.contains(xy)) {
            Log.d("check","yes");
            btn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.xy3));
            points += 3;
        }

        if (bombXY.contains(xy)) {
            Log.d("check","bombin");

            for (String s : bombXY) {
                String str = "xy" + s;
                Log.d("check","bomb1");
                int id = getResources().getIdentifier(str, "id", requireActivity().getPackageName());
                Log.d("check","bomb2");
                ImageButton imgBtn = requireActivity().findViewById(id);
                Log.d("check","bomb3");
                imgBtn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.bomb));
                Log.d("check","bomb4");
                YoYo.with(Techniques.FlipInY).playOn(imgBtn);

                Log.d("check","bomb5");
            }

            Log.d("check","bombout");

            btn.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.bomb));
            YoYo.with(Techniques.FlipInY).playOn(btn);

            flag = 1;
            mp = MediaPlayer.create(requireActivity(), R.raw.bomb_sound);
            mp.start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    customDialog cd = new customDialog();
                    Log.d("points", "value : " + points);
                    cd.show(requireActivity(), points);


                    setNewGame();
                }
            }, 1000);

        }

        String str = "";

        if (points < 10)
            str = "0" + points;

        else str = "" + points;

        pointsTxt.setText(str);
    }

    public void setNewGame() {

        for (String s : xy_0) {
            String str = "xy" + s;
            int resId = getResources().getIdentifier(str, "id", getActivity().getPackageName());

            ImageButton btn = getActivity().findViewById(resId);

            btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button));
        }

        for (String s : xy_1) {
            String str = "xy" + s;
            int resId = getResources().getIdentifier(str, "id", getActivity().getPackageName());

            ImageButton btn = getActivity().findViewById(resId);

            btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button));
        }

        for (String s : xy_2) {
            String str = "xy" + s;
            int resId = getResources().getIdentifier(str, "id", getActivity().getPackageName());

            ImageButton btn = getActivity().findViewById(resId);

            btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button));
        }

        for (String s : xy_3) {
            String str = "xy" + s;
            int resId = getResources().getIdentifier(str, "id", getActivity().getPackageName());

            ImageButton btn = getActivity().findViewById(resId);

            btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button));
        }

        for (String s : bombXY) {
            String str = "xy" + s;
            int resId = getResources().getIdentifier(str, "id", getActivity().getPackageName());

            ImageButton btn = getActivity().findViewById(resId);

            btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button));
        }

        xy_0.clear();
        xy_1.clear();
        xy_2.clear();
        xy_3.clear();
        bombXY.clear();
        flag = 0;

        setXY();
        points = 0;
        pointsTxt.setText("00");


    }

}