package com.brioal.libmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.libmanager.MainActivity;
import com.brioal.libmanager.R;
import com.brioal.libmanager.activity.AdTextViewActivity;
import com.brioal.libmanager.activity.CircleHeadActivity;
import com.brioal.libmanager.activity.CirclePointActivity;
import com.brioal.libmanager.activity.ColdStartPracticeOne;
import com.brioal.libmanager.activity.ColdStartPracticeTwo;
import com.brioal.libmanager.activity.GradualGuideActivity;
import com.brioal.libmanager.activity.LineProgressActivity;
import com.brioal.libmanager.activity.SoftInputAdjustActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Brioal on 2016/7/20.
 */

public class MainTestAdapter extends RecyclerView.Adapter<MainTestAdapter.TestViewHolder> {


    private Context mContext;
    private List<String> mList;


    public MainTestAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void addAll(List<String> items) {
        int pos = getItemCount();
        mList.addAll(items);
        notifyItemRangeInserted(pos, mList.size());
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {
        holder.mTextView.setText(mList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        CirclePointActivity.startActivity(mContext, CirclePointActivity.class);
                        break;
                    case 1:
                        GradualGuideActivity.startActivity(mContext, GradualGuideActivity.class);
                        break;
                    case 2:
                        SoftInputAdjustActivity.startActivity(mContext, SoftInputAdjustActivity.class);
                        break;
                    case 3:
                        CircleHeadActivity.startActivity(mContext, CircleHeadActivity.class);
                        break;
                    case 4:
                        LineProgressActivity.startActivity(mContext, LineProgressActivity.class);
                        break;
                    case 5:
                        AdTextViewActivity.startActivity(mContext, AdTextViewActivity.class);
                        break;
                    case 6:
                        ColdStartPracticeOne.startActivity(mContext, ColdStartPracticeOne.class);
                        break;
                    case 7:
                        ColdStartPracticeTwo.startActivity(mContext, ColdStartPracticeTwo.class);
                        break;
                    case 8:
                        MainActivity.startActivity(mContext, MainActivity.class);
                        ((Activity) mContext).finish();
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_test_title)
        TextView mTextView;

        View itemView;

        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}

