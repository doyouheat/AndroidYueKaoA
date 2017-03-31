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
 * 3. @date 2017/3/31 10:58
 */
public class MyBaseAdapter extends BaseAdapter {
    List<Shopping.ResultBean.BrandsBean.ProductsBean>  list;
    Context context;
    public MyBaseAdapter(List<Shopping.ResultBean.BrandsBean.ProductsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  v;
        if (convertView==null){
            v=new ViewHolder();
            convertView=View.inflate(context, R.layout.brand_iteam_layout,null);
            v.iv_wares= (ImageView) convertView.findViewById(R.id.iv_wares);
            v.name= (TextView) convertView.findViewById(R.id.name);
            v.sale= (TextView) convertView.findViewById(R.id.sale);
            v.price= (TextView) convertView.findViewById(R.id.price);
            v.count= (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(v);
        }else {
            v= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getPic()).into(v.iv_wares);
        v.name.setText(list.get(position).getName());
        v.sale.setText("已成交："+list.get(position).getSaleCounts());
        v.price.setText("价格："+list.get(position).getPrice());
        v.count.setText("库存："+list.get(position).getStock());
        return convertView;
    }

    static  class  ViewHolder{
        ImageView  iv_wares;
        TextView  name,sale,price,count;
    }
}
