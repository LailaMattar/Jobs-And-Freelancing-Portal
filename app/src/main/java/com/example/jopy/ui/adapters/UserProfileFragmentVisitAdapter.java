package com.example.jopy.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jopy.ui.fragments.user_profile_fragments.CvFragment;
import com.example.jopy.ui.fragments.user_profile_fragments.FreelancesFragment;
import com.example.jopy.ui.fragments.user_profile_fragments.ServicesFragment;
import com.example.jopy.ui.fragments.visite_profile_user.VisitUserCv;
import com.example.jopy.ui.fragments.visite_profile_user.VisitUserFreelance;
import com.example.jopy.ui.fragments.visite_profile_user.VisitUserService;

public class UserProfileFragmentVisitAdapter extends FragmentStateAdapter {

    public UserProfileFragmentVisitAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new VisitUserCv();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
