package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	private final List<Fragment> fragmentList = new ArrayList<>();
	private final List<String> FragmentListTitles = new ArrayList<>();


	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public ViewPagerAdapter(FragmentManager childFragmentManager, MisCursosDetalleFragment misCursosDetalleFragment) {
		super(childFragmentManager);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return FragmentListTitles.get(position);
	}


	public void AddFragment(Fragment fragment, String Title){
		fragmentList.add(fragment);
		FragmentListTitles.add(Title);
	}
}
