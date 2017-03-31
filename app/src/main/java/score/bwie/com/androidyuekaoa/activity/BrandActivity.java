package score.bwie.com.androidyuekaoa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import score.bwie.com.androidyuekaoa.R;
import score.bwie.com.androidyuekaoa.adapter.MyBaseAdapter;
import score.bwie.com.androidyuekaoa.bean.Shopping;

import static android.R.id.list;

public class BrandActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv;
    private ImageView iv_back;
    private List alist;
    private MyBaseAdapter adapter;
    private Shopping.ResultBean.BrandsBean extra;
    private TextView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        initView();
        extra = (Shopping.ResultBean.BrandsBean) getIntent().getSerializableExtra("bean");
        String title = extra.getTitle();
        head.setText(title);
        List<Shopping.ResultBean.BrandsBean.ProductsBean> list = extra.getProducts();
        adapter = new MyBaseAdapter(list, this);
        lv.setAdapter(adapter);
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        head = (TextView) findViewById(R.id.head);
        iv_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.iv_back:
                finish();
                break;
        }
    }
}
