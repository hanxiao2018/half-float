package com.example.hanxiao.dialogfragment.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hanxiao.dialogfragment.R;
import com.example.hanxiao.dialogfragment.dialogfragment.ConcreteDialogFragment;

public class MainActivity extends AppCompatActivity {

    private Button mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShow = (Button) findViewById(R.id.show);

        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConcreteDialogFragment()
                        .show(getFragmentManager(), "MainActivity");
            }
        });
    }
}
