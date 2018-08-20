package popup.popfisher.com.smartpopupwindow.my.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import popup.popfisher.com.smartpopupwindow.R;

public class MainActivity extends Activity {

    private PopupWindow mPopWindow;
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopupWindow();
                show();
            }
        });
    }

    private void showPopupWindow() {
        List<String> dataList = new ArrayList<>();
        dataList.add("123");
        dataList.add("123");
        dataList.add("123");
        dataList.add("123");
        dataList.add("123");
        dataList.add("123");
        dataList.add("123");

        //设置contentView
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.pop_test, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, 200, true);
        ListView listView = (ListView) contentView.findViewById(R.id.lv_test);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String string= (String) adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void show() {
        new MenuPopupWindow(MainActivity.this, new MenuPopupWindow.ClickResultListener() {
            @Override
            public void ClickResult(boolean tag) {
                if (tag) {
                    //同步到本地
                    Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
                } else {
                    //上传
                    Toast.makeText(MainActivity.this, "222", Toast.LENGTH_SHORT).show();

                }
            }
        }).showAsDropDown(btn, 0, 0);
    }


}