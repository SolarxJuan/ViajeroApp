package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.models.Trip;
import com.example.myapplication.utils.RatingUtils;
import java.util.List;

public class TripHistoryAdapter extends RecyclerView.Adapter<TripHistoryAdapter.ViewHolder> {

    private List<Trip> tripList;
    private final OnTripClickListener listener;

    public interface OnTripClickListener {
        void onTripClick(Trip trip);
    }

    public TripHistoryAdapter(List<Trip> tripList, OnTripClickListener listener) {
        this.tripList = tripList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trip_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.bind(trip, listener);
    }

    @Override
    public int getItemCount() {
        return tripList != null ? tripList.size() : 0;
    }

    public void updateTrips(List<Trip> newTrips) {
        this.tripList = newTrips;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // CORREGIDO: tvDestination y tvOrigin ya no son nulos
        private final TextView tvDestination;
        private final TextView tvDate;
        private final TextView tvOrigin;
        private final TextView tvPrice;
        private final LinearLayout ratingContainer;
        private final ImageView ivStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Estos findViewById ahora funcionarán gracias a las correcciones en el XML
            tvDestination = itemView.findViewById(R.id.tv_destination);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvOrigin = itemView.findViewById(R.id.tv_origin);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ratingContainer = itemView.findViewById(R.id.rating_container);
            ivStatus = itemView.findViewById(R.id.iv_status);
        }

        public void bind(Trip trip, OnTripClickListener listener) {
            // LÍNEA 73 ORIGINAL (Ahora corregida porque tvDestination ya no es nulo)
            tvDestination.setText(trip.getDestination());

            tvDate.setText(trip.getDate() != null ? trip.getDate() : "");
            tvOrigin.setText(trip.getOrigin());
            tvPrice.setText(String.format("$%.2f", trip.getPrice()));

            // Configurar rating
            if (ratingContainer.getChildCount() == 0) {
                RatingUtils.createStars(ratingContainer, trip.getRating());
            } else {
                RatingUtils.setRatingStars(ratingContainer, trip.getRating());
            }

            // Configurar icono de estado
            if ("completed".equals(trip.getStatus())) {
                ivStatus.setImageResource(R.drawable.ic_check_circle);
                ivStatus.setColorFilter(itemView.getContext().getColor(R.color.success));
            } else if ("cancelled".equals(trip.getStatus())) {
                ivStatus.setImageResource(R.drawable.ic_cancel);
                ivStatus.setColorFilter(itemView.getContext().getColor(R.color.error));
            } else if ("in_progress".equals(trip.getStatus())) {
                ivStatus.setImageResource(R.drawable.ic_time);
                ivStatus.setColorFilter(itemView.getContext().getColor(R.color.warning));
            }

            itemView.setOnClickListener(v -> listener.onTripClick(trip));
        }
    }
}