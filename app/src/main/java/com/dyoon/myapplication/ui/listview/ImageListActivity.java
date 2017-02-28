package com.dyoon.myapplication.ui.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dyoon.myapplication.adapter.ImageListAdapter;
import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/2/16.
 */

@ContentView(R.layout.activity_img_list)
public class ImageListActivity extends Activity {
    @ViewInject(R.id.img_list_view)
    private ListView listView;
    private List<String> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initList();
    }

    private void initList() {
        imgList.add("http://img.hb.aicdn.com/4b67d6dc20a5b5c9fce02b8a58b96d72bda8563016427-wCY09V_fw658");
        imgList.add("http://img.hb.aicdn.com/59c79d5431521065199bcfbef4a1231567cc7840a0bb-OPmDHd_fw658");
        imgList.add("http://img.hb.aicdn.com/7744fbd2cbeb1f5933ef1a13aeb103ac0e85d51f937fb-tHzPUB_fw658");
        imgList.add("http://img.hb.aicdn.com/bd8de898a3ee40d01c6c8265d6398078d047e2301624b-8Hd3ou_fw658");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageListAdapter ila = new ImageListAdapter(imgList, this);
        listView.setAdapter(ila);
    }
}
