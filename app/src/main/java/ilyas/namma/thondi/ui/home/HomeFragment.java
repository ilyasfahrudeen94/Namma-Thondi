package ilyas.namma.thondi.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ilyas.namma.thondi.MainActivity;
import ilyas.namma.thondi.NewsRecycler;
import ilyas.namma.thondi.database.RealmHelper;
import ilyas.namma.thondi.database.SpaceCraft;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import com.ilyas.testing.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.ic_menu_camera, R.drawable.side_nav_bar, R.drawable.ic_menu_gallery, R.drawable.ic_menu_slideshow, R.drawable.ic_menu_send};

    Realm realm;
    ArrayList<String> spacecrafts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                System.out.println("position----->"+position);
            }
        });

        //SETUP REEALM
        RealmConfiguration config=new RealmConfiguration.Builder(getContext()).build();
        realm= Realm.getInstance(config);
        //RETRIEVE
        final RealmHelper helper=new RealmHelper(realm);
        spacecrafts=helper.retrieve();


        for(int i = 0; i<10; i++) {
            SpaceCraft s = new SpaceCraft();
            s.setName("my position--->"+i);

            //SAVE
            helper.save(s);
        }
        //REFRESH
        spacecrafts=helper.retrieve();




        final NewsRecycler newsRecycler = new NewsRecycler(getContext(), spacecrafts);
        final RecyclerView recyclerView = root.findViewById(R.id.news_home_recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager  mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(newsRecycler);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 10; i<20; i++) {
                    SpaceCraft s = new SpaceCraft();
                    s.setName("my position--->"+i);

                    //SAVE
                    helper.save(s);
                }
                //REFRESH
                spacecrafts=helper.retrieve();
                final NewsRecycler newsRecycler = new NewsRecycler(getContext(), spacecrafts);
                recyclerView.setAdapter(newsRecycler);

            }
        }, 5000);

        return root;
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}