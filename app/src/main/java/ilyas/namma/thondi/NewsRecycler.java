package ilyas.namma.thondi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilyas.testing.R;

import java.util.ArrayList;

public class NewsRecycler extends RecyclerView.Adapter<NewsRecycler.NewsRecyclerViewHolder>{

    Context context;
    ArrayList<String> spacecrafts;

    public NewsRecycler(Context context, ArrayList<String> spaceCraft) {
        this.context = context;
        this.spacecrafts = spaceCraft;
    }

    @NonNull
    @Override
    public NewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item, parent, false);
        NewsRecyclerViewHolder newsRecyclerViewHolder = new NewsRecyclerViewHolder(view);
        return newsRecyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewHolder holder, int position) {
        holder.text.setText(spacecrafts.get(position));
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    class NewsRecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView childImage, play_icon;
        RelativeLayout more_image_view;
        TextView text;

        public NewsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
                }
    }
}
