package com.lfeng.eeliu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTabIndicatorAdapter extends FragmentPagerAdapter {
    
    public BaseTabIndicatorAdapter(FragmentManager fm,List<Fragment> fragments,List<String> tiltes) {
		super(fm);
		this.fragments = fragments;
		this.tiltes = tiltes;
	}
	public BaseTabIndicatorAdapter(FragmentManager fm,List<Fragment> fragments,String[] tiltes) {
		super(fm);
		this.fragments = fragments;
		this.tiltes = Arrays.asList(tiltes);
	}
	private List<String> tiltes = new ArrayList<String>();
	private List<Fragment> fragments = new ArrayList<Fragment>();

	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return tiltes.get(position);
	}
	


}