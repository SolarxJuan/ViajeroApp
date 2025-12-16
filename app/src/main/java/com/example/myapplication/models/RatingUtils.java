package com.example.myapplication.models;



import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.example.myapplication.R;

public class RatingUtils {

    public static void setRatingStars(LinearLayout container, float rating) {
        Context context = container.getContext();

        for (int i = 0; i < 5; i++) {
            ImageView star = (ImageView) container.getChildAt(i);
            if (star == null) continue;

            if (i < Math.floor(rating)) {
                // Estrella completa
                star.setImageResource(R.drawable.ic_star_filled);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_active));
            } else if (i < rating) {
                // Media estrella
                star.setImageResource(R.drawable.ic_star_half);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_active));
            } else {
                // Estrella vacía
                star.setImageResource(R.drawable.ic_star_outline);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_inactive));
            }
        }
    }

    public static void createStars(LinearLayout container, float rating) {
        Context context = container.getContext();
        container.removeAllViews();

        for (int i = 0; i < 5; i++) {
            ImageView star = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dpToPx(context, 20),
                    dpToPx(context, 20)
            );
            params.setMargins(dpToPx(context, 2), 0, dpToPx(context, 2), 0);
            star.setLayoutParams(params);

            if (i < Math.floor(rating)) {
                star.setImageResource(R.drawable.ic_star_filled);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_active));
            } else if (i < rating) {
                star.setImageResource(R.drawable.ic_star_half);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_active));
            } else {
                star.setImageResource(R.drawable.ic_star_outline);
                star.setColorFilter(ContextCompat.getColor(context, R.color.star_inactive));
            }

            container.addView(star);
        }
    }

    private static int dpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static String getRatingText(float rating) {
        if (rating >= 4.5) {
            return "Excelente";
        } else if (rating >= 4.0) {
            return "Muy bueno";
        } else if (rating >= 3.0) {
            return "Bueno";
        } else if (rating >= 2.0) {
            return "Regular";
        } else {
            return "Malo";
        }
    }
}
