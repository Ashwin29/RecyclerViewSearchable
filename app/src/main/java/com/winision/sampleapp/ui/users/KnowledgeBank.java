package com.winision.sampleapp.ui.users;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
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
    private FloatingActionButton backKB;

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

        backKB = view.findViewById(R.id.backKB);

        backKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

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

    private void back() {

        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 4);
        }

    }


}
