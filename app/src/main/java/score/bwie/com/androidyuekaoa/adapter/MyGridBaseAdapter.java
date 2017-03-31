package score.bwie.com.androidyuekaoa.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import score.bwie.com.androidyuekaoa.R;
import score.bwie.com.androidyuekaoa.bean.Shopping;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/31 14:11
 */
public class MyGridBaseAdapter extends BaseAdapter {
    List<Shopping.ResultBean.IndexProductsBean>  list;
    Context  context;

    public MyGridBaseAdapter(List<Shopping.ResultBean.IndexProductsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return   list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder v;
        if (convertView==null){
            v=new ViewHolder();
            convertView=View.inflate(context, R.layout.grid_iteam_layout,null);
            v.iv_grid= (ImageView) convertView.findViewById(R.id.iv_grid);
            v.grid_name= (TextView) convertView.findViewById(R.id.grid_name);
            v.marketPrice = (TextView) convertView.findViewById(R.id.marketPrice);
            v.grid_price = (TextView) convertView.findViewById(R.id.grid_price);
            convertView.setTag(v);
        }else {
            v= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getPic()).into(v.iv_grid);
        v.grid_name.setText(list.get(position).getName());
        v.marketPrice.setText("原价："+list.get(position).getMarketPrice());
        v.grid_price .setText("优惠价："+list.get(position).getPrice());
        return convertView;
    }

    static  class  ViewHolder{
        ImageView  iv_grid;
        TextView  grid_name,marketPrice,grid_price;
    }
}
