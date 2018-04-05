package ru.vladus177.n3tworktest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ItemOneFragment extends Fragment implements View.OnClickListener {

    EditText et1, et2;
    Button btn;
    TextView result;
    String[] testArray;


    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) {
            et1 = getView().findViewById(R.id.et1_1);
            et2 = getView().findViewById(R.id.et1_2);
            result = getView().findViewById(R.id.tv1_result);
            btn = getView().findViewById(R.id.btn1_run_test);
            btn.setOnClickListener(this);

            initTest();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    private void initTest() {
        String line = "1, 8, 7, 2, 9, 6, 3, 4, 5";
        testArray = line.split(",");
        String test = TextUtils.join(" ", testArray);
        et1.setText(test);
        et2.setText("3");
    }

    private void runTest() {

        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (String s : testArray) {
            for (int i =0; i <3; i++ ) {
                sb.append(s);
            }

        }
        sb.append("|");
        result.setText(sb.toString());
    }

    private void showMsg (String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1_run_test: {
                runTest();
            }
        }
    }
}
