package com.example.myapplication.utils;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class RatingUtils {

    public static void setRatingStars(LinearLayout container, float rating) {
        if (container == null) return;

        container.removeAllViews();

        int fullStars = (int) rating;
        boolean hasHalfStar = (rating - fullStars) >= 0.5f;

        // Agregar estrellas llenas
        for (int i = 0; i < fullStars; i++) {
            ImageView star = new ImageView(container.getContext());
            star.setImageResource(R.drawable.ic_star_filled);
            star.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            container.addView(star);
        }

        // Agregar media estrella si es necesario
        if (hasHalfStar) {
            ImageView star = new ImageView(container.getContext());
            star.setImageResource(R.drawable.ic_star_half);
            star.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            container.addView(star);
        }

        // Agregar estrellas vacías
        int emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);
        for (int i = 0; i < emptyStars; i++) {
            ImageView star = new ImageView(container.getContext());
            star.setImageResource(R.drawable.ic_star_outline);
            star.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            container.addView(star);
        }
    }

    public static void createStars(LinearLayout container, float rating) {
        setRatingStars(container, rating);
    }
}