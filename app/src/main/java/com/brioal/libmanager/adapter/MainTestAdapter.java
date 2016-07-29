package com.brioal.libmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.libmanager.R;
import com.brioal.libmanager.activity.TestActivity;
import com.brioal.libmanager.entity.DemoEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**主界面RecyclerView适配器
 * Created by Brioal on 2016/7/20.
 */

public class MainTestAdapter extends RecyclerView.Adapter<MainTestAdapter.TestViewHolder> {


    private Context mContext;
    private List<DemoEntity> mList;


    public MainTestAdapter(Context context, List<DemoEntity> list) {
        mContext = context;
        mList = list;
    }

    public void addAll(List<DemoEntity> items) {
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
        final DemoEntity entity = mList.get(position);
        holder.mTextView.setText(entity.getDesc());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.startActivity(mContext, entity.getclass());

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

