package score.bwie.com.androidyuekaoa.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import score.bwie.com.androidyuekaoa.R;
import score.bwie.com.androidyuekaoa.adapter.MyGridBaseAdapter;
import score.bwie.com.androidyuekaoa.bean.Shopping;
import score.bwie.com.androidyuekaoa.utils.HttpUtils;

public class MainActivity extends AppCompatActivity implements HttpUtils.SuccessInterface,View.OnClickListener{

    private ViewPager vp;
    private String url="http://www.babybuy100.com/API/getShopOverview.ashx";
    private ArrayList<ImageView>  ivList=new ArrayList<>();
    private MyPagerAdapter adapter;
    private  ArrayList<View>  v_list=new ArrayList<>();
    private LinearLayout lin;
        Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what==0){
            int item = vp.getCurrentItem();
            if (item==5){
                item=0;
                vp.setCurrentItem(item);
                handler.sendEmptyMessageDelayed(0,2000);
            }else {
                item++;
                vp.setCurrentItem(item);
                handler.sendEmptyMessageDelayed(0,2000);
            }
        }
    }
};
    private ImageView iv1;
    private ImageView iv2;
    private Shopping.ResultBean.BrandsBean bean;
    private Shopping.ResultBean.BrandsBean bean1;
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        lin = (LinearLayout) findViewById(R.id.lin);
        iv1 = (ImageView) findViewById(R.id.iv_btn1);
        iv2 = (ImageView) findViewById(R.id.iv_btn2);
        gridView = (GridView) findViewById(R.id.gridview);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        HttpUtils.getConnect(this,url,this);
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void succecc(String res) {
        Shopping shopping = new Gson().fromJson(res, Shopping.class);
        Shopping.ResultBean result = shopping.getResult();

        List<Shopping.ResultBean.AdvsBean> advsList = result.getAdvs();
                  createImageView(advsList,advsList.size());

        List<Shopping.ResultBean.BrandsBean> brandsList = result.getBrands();
                setBrand(brandsList);


        List<Shopping.ResultBean.IndexProductsBean> indexProductsList = result.getIndexProducts();
        MyGridBaseAdapter gridBaseAdapter = new MyGridBaseAdapter(indexProductsList, this);
        gridView.setAdapter(gridBaseAdapter);
    }

    private void setBrand(List<Shopping.ResultBean.BrandsBean> list) {
        Picasso.with(this).load(list.get(0).getPic()).into(iv1);
        Picasso.with(this).load(list.get(1).getPic()).into(iv2);
        bean = list.get(0);
        bean1 = list.get(1);
    }

    private void createImageView( List<Shopping.ResultBean.AdvsBean> advsList ,int size) {
        for (int i = 0; i < size; i++) {
            ImageView  image=new ImageView(this);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(this).load(advsList.get(i).getPic()).into(image);


            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.rightMargin=10;
            lin.addView(view,params);
            view.setSelected(false);
            v_list.add(view);
            v_list.get(0).setSelected(true);

            ivList.add(image);
        }
       vp.setAdapter( new MyPagerAdapter());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < v_list.size(); i++) {
                    if (i == position % v_list.size()) {
                        v_list.get(i).setSelected(true);
                    } else {
                        v_list.get(i).setSelected(false);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
         switch (v.getId())
         {
             case  R.id.iv_btn1:
                 Intent intent = new Intent(MainActivity.this,BrandActivity.class);
                 intent.putExtra("bean", bean);
                 startActivity(intent);
                 break;
             case  R.id.iv_btn2:
                 Intent intent1 = new Intent(MainActivity.this,BrandActivity.class);
                 intent1.putExtra("bean", bean1);
                 startActivity(intent1);
                 break;
         }
    }


    class   MyPagerAdapter  extends PagerAdapter{
        @Override
        public int getCount() {
            return  ivList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return   view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = ivList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(ivList.get(position));
        }
    }
}
