package com.winision.sampleapp.ui.users;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.winision.sampleapp.R;
import com.winision.sampleapp.ViewPagerAdapterContacts;


public class KnowledgeBank extends Fragment {

    ViewPagerAdapterContacts viewPagerAdapterContacts;
    PDF_Fragment pdf_fragment;
    Videos_Fragment videos_fragment;
    Images_Fragment images_fragment;
    Notes_Fragment notes_fragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActionBar toolbar;

    public KnowledgeBank() {
        // Required empty public constructor
    }

    public static KnowledgeBank newInstance(String param1, String param2) {
        KnowledgeBank fragment = new KnowledgeBank();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge_bank, container, false);

        viewPager = view.findViewById(R.id.viewPagerKB);
        viewPager.setOffscreenPageLimit(4);

        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tablayoutKB);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_pdf:
                loadFragment(new PDF_Fragment());
                return true;
            case R.id.navigation_videos:
                loadFragment(new Videos_Fragment());
                return true;
            case R.id.navigation_images:
                loadFragment(new Images_Fragment());
                return true;
            case R.id.navigation_notes:
                loadFragment(new Notes_Fragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapterContacts = new ViewPagerAdapterContacts(getChildFragmentManager());


        pdf_fragment = new PDF_Fragment();
        videos_fragment = new Videos_Fragment();
        notes_fragment = new Notes_Fragment();
        images_fragment = new Images_Fragment();

        viewPagerAdapterContacts.addfragment(pdf_fragment, "PDF");
        viewPagerAdapterContacts.addfragment(videos_fragment, "Videos");
        viewPagerAdapterContacts.addfragment(notes_fragment, "Notes");
        viewPagerAdapterContacts.addfragment(images_fragment, "Images");

        viewPager.setAdapter(viewPagerAdapterContacts);
    }


    private void loadFragment(Fragment fragment) {
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);

        fragmentTransaction.commit();
    }


}
