package ru.vladus177.n3tworktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ItemTwoFragment extends Fragment implements View.OnClickListener {

    EditText et1;
    Button btn;
    TextView result;
    ArrayList<String> primes;
    int count;
    long countLong;
    boolean isLong;

    public static ItemTwoFragment newInstance() {

        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() != null) {
            et1 = getView().findViewById(R.id.et2_2);
            result = getView().findViewById(R.id.tv2_result);
            btn = getView().findViewById(R.id.btn2_run_test);
            btn.setOnClickListener(this);
            primes = new ArrayList<>();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item_two, container, false);
    }

    private void runTest() {

        isLong = false;
        try {
            count = Integer.parseInt(et1.getText().toString());
        } catch (NumberFormatException e) {
            showMsg("Looks number is too big.... Processing will take longer.");
            isLong = true;
        }

        if (isLong) {
            try {
                countLong = Long.parseLong(et1.getText().toString());
            } catch (NumberFormatException e) {
                showMsg("The number is too big, cannot be processed. Or number contains prohibited symbols. Please try another number.");
                return;
            }
        }

        new TextLoader().execute();
    }

    private void showResult(String s) {

        result.setText(s);
    }

    private void showMsg(String msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn2_run_test: {
                runTest();
            }
        }
    }

    public class TextLoader extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            if (isLong) {
                fillPrimesArrayLong(countLong);
            } else {
                fillPrimesArray(count);
            }

            String result = formText();

            return formText();
        }

        @Override
        protected void onPostExecute(String s) {

            showResult(s);
        }
    }

    private void fillPrimesArray(int count) {

        if (count > Constant.TEXT_LENGTH) {
            count = Constant.TEXT_LENGTH;
        }

        primes.clear();

        for (int i = 1; i <= count; i++) {
            if (checkIsPrime(i)) {
                primes.add(String.valueOf(i));
            } else {
                count++;
            }

        }

    }

    private void fillPrimesArrayLong(long count) {

        if (count > Constant.TEXT_LENGTH) {
            count = Constant.TEXT_LENGTH;
        }

        primes.clear();

        for (int i = 1; i <= count; i++) {
            if (checkIsPrime(i)) {
                primes.add(String.valueOf(i));
            } else {
                count++;
            }

        }
    }

    private boolean checkIsPrime(int n) {


        int i;
        for (i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private String formText() {
        StringBuilder sb = new StringBuilder();
        for (String s : primes) {
            sb.append(s);
            sb.append(" ");
        }

        return sb.toString();
    }
}
