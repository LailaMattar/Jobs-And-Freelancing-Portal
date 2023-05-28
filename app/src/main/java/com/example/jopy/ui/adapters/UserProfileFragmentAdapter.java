package com.example.jopy.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jopy.ui.fragments.user_profile_fragments.CvFragment;
import com.example.jopy.ui.fragments.user_profile_fragments.FreelancesFragment;
import com.example.jopy.ui.fragments.user_profile_fragments.ReviewsFragments;
import com.example.jopy.ui.fragments.user_profile_fragments.ServicesFragment;

public class UserProfileFragmentAdapter extends FragmentStateAdapter {
    public UserProfileFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new FreelancesFragment();
            case 2 :
                return new ServicesFragment();
        }
        return new CvFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
