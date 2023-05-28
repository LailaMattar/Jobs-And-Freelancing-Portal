package com.example.jopy.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jopy.ui.fragments.institute_profile_fragment.AboutFragment;
import com.example.jopy.ui.fragments.institute_profile_fragment.JobsFragment;
import com.example.jopy.ui.fragments.institute_profile_fragment.ReviewFragment;

public class InstituteProfileFragmentAdapter extends FragmentStateAdapter {
    public InstituteProfileFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new JobsFragment();
            case 2:
                return new ReviewFragment();
        }
        return new AboutFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
