package com.brioal.libmanager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brioal.libmanager.R;

/**
 * Created by Brioal on 2016/7/21.
 */

public class GuideViewPager extends PagerAdapter {
    private Context mContext;
    private int[] icons = new int[]{
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    public GuideViewPager(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemLayout = LayoutInflater.from(mContext).inflate(R.layout.item_guide, container, false);
        ImageView mImage = (ImageView) itemLayout.findViewById(R.id.item_guide_iv_image);
        TextView mTextView = (TextView) itemLayout.findViewById(R.id.item_guide_tv_indicator);
        mImage.setImageResource(icons[position]);
        mTextView.setText(position + "");
        container.addView(itemLayout);
        return itemLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewGroup) container).removeView((View) object);
    }
}
