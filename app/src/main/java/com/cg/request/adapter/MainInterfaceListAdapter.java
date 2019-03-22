package com.cg.request.adapter;

import com.cg.request.R;
import com.cg.request.data.MainInterfaceItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MainInterfaceListAdapter extends BaseQuickAdapter<MainInterfaceItem, BaseViewHolder> {
    public MainInterfaceListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MainInterfaceItem dataIndex) {
        baseViewHolder.setText(R.id.list_item_name, dataIndex.getName());
        baseViewHolder.setBackgroundColor(R.id.list_item_color, dataIndex.getBackgroundColor());
    }
}