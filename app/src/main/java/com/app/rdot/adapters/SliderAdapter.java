package com.app.rdot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.rdot.R;

public class SliderAdapter extends PagerAdapter {

    private final Context context;
    private final int[] sliderImages;

    public SliderAdapter(Context context, int[] sliderImages) {
        this.context = context;
        this.sliderImages = sliderImages;
    }

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_item, container, false);

        ImageView sliderImage = view.findViewById(R.id.sliderImage);
        sliderImage.setImageResource(sliderImages[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}