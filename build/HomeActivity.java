package com.example.girlnyshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout llHome, llSearch, llChat, llProfile;
    private ImageView ivHome, ivChat, ivProfile;
    private View ivCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        llHome = findViewById(R.id.llHome);
        llSearch = findViewById(R.id.llSearch);
        llChat = findViewById(R.id.llChat);
        llProfile = findViewById(R.id.ll_profile);
        ivCart = findViewById(R.id.ivCart);

        ivHome = findViewById(R.id.ivHome);
        ivChat = findViewById(R.id.ivChat);
        ivProfile = findViewById(R.id.ivProfile);

        ivHome.setImageResource(R.drawable.ic_home_selected);
        ivChat.setImageResource(R.drawable.ic_chat);
        ivProfile.setImageResource(R.drawable.ic_profile);

        llHome.setOnClickListener(v -> {
            ivHome.setImageResource(R.drawable.ic_home_selected);
            ivChat.setImageResource(R.drawable.ic_chat);
            ivProfile.setImageResource(R.drawable.ic_profile);
            loadFragment(new HomeFragment());
            ivCart.setVisibility(View.VISIBLE);
        });
        llSearch.setOnClickListener(v -> {

        });
        llChat.setOnClickListener(v -> {
            ivHome.setImageResource(R.drawable.ic_home);
            ivChat.setImageResource(R.drawable.ic_chat_selected);
            ivProfile.setImageResource(R.drawable.ic_profile);
            loadFragment(new ChatFragment());
            ivCart.setVisibility(View.GONE);
        });
        llProfile.setOnClickListener(v -> {
            ivHome.setImageResource(R.drawable.ic_home);
            ivChat.setImageResource(R.drawable.ic_chat);
            ivProfile.setImageResource(R.drawable.ic_profile_selected);
            loadFragment(new ProfileFragment());
            ivCart.setVisibility(View.GONE);
        });
        ivCart.setOnClickListener(v -> {
            loadFragment(new CartFragment());
            ivCart.setVisibility(View.GONE);
        });

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
