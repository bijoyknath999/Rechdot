package com.app.rdot.pages;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.app.rdot.R;
import com.app.rdot.adapters.ServiceAdapter;
import com.app.rdot.adapters.SliderAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ViewPager slider;
    private LinearLayout dotsLayout;
    private int[] sliderImages = {
            R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3
    };

    private List<Pair<Integer, String>> services = new ArrayList<>();

    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_home);

        // Add images with titles to the list
        services.add(new Pair<>(R.drawable.ic_phone, "Mobile"));
        services.add(new Pair<>(R.drawable.ic_dth, "DTH"));
        services.add(new Pair<>(R.drawable.ic_electricity, "Electricity"));
        services.add(new Pair<>(R.drawable.ic_card, "Credit Card\nPayment"));
        services.add(new Pair<>(R.drawable.ic_phone, "Zip\nRepayment"));
        services.add(new Pair<>(R.drawable.ic_wifi, "LPG Booking"));
        services.add(new Pair<>(R.drawable.ic_electricity, "LIC/Insurance"));
        services.add(new Pair<>(R.drawable.ic_electricity, "Google Play\nRecharge"));
        // Initialize Slider (ViewPager)
        slider = findViewById(R.id.slider);

        // Initialize RecyclerView for Service Layout
        RecyclerView serviceRecyclerView = findViewById(R.id.serviceRecyclerView);
        serviceRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        ServiceAdapter serviceAdapter = new ServiceAdapter(services);
        serviceRecyclerView.setAdapter(serviceAdapter);


        slider = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dotsLayout);
        slider.setPageMargin(30);

        SliderAdapter sliderAdapter = new SliderAdapter(this, sliderImages);
        slider.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                addDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setupAutoSlider();
    }

    private void addDotsIndicator(int position) {
        dotsLayout.removeAllViews();
        dots = new ImageView[sliderImages.length];

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getDrawable(R.drawable.dot_inactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 15, 8, 15);
            dotsLayout.addView(dots[i], params);
        }

        if (dots.length > 0) {
            dots[position].setImageDrawable(getDrawable(R.drawable.dot_active));
        }
    }

    private void setupAutoSlider() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {

            int currentPage = 0;

            @Override
            public void run() {
                if (currentPage == sliderImages.length) {
                    currentPage = 0;
                }

                slider.setCurrentItem(currentPage, true);
                currentPage = currentPage + 1;
                handler.postDelayed(this, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);
    }
}