package com.faustoc.consumoapi2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.faustoc.consumoapi2.R;
import com.faustoc.consumoapi2.model.StoreProducts;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<StoreProducts> lst;

    public CustomAdapter(Context context, List<StoreProducts> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView product_image;
        TextView tvInfo;

        StoreProducts sp = lst.get(i);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listview_item,null);

        //obtenemos objetos de la vista
        product_image = view.findViewById(R.id.imageView);
        tvInfo = view.findViewById(R.id.tvInfo);

        product_image.setImageResource(sp.getImage());
        tvInfo.setText(sp.toString());

        return  view;
    }
}
