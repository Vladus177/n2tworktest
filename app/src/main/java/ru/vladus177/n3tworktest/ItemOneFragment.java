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
    TextView result, example;
    String[] testArray;
    int lineSize;

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
            example = getView().findViewById(R.id.tv2_example);
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

        String line = "1 8 7 2 9 6 3 4 5";

        testArray = line.split(" ");
        String test = TextUtils.join(" ", testArray);
        et1.setText(test);
        et2.setText("3");
    }

    private void createArrayFromString(String str, int columns) {
        String[] newArray = str.split(" ");

        if (columns > Constant.SCREEN_SIZE_LIMIT) {
            columns = Constant.SCREEN_SIZE_LIMIT;
        }

        int rows = (testArray.length / columns);

        if (rows > Constant.SCREEN_SIZE_LIMIT) {
            rows = Constant.SCREEN_SIZE_LIMIT;
        }

        String a[][] = new String[rows][columns];

        int i = 0;

        for (int r = 0; r < a.length; r++) {

            for (int c = 0; c < a[r].length; c++) {

                a[r][c] = newArray[i];
                i++;

            }
        }

        if (a.length > 0) {
            buildMatrix(a, rows, columns);
        }
    }

    private Boolean checkArrayLimits() {

        boolean isArrayOk = true;
        if (testArray != null && testArray.length > 0) {
            for (String s : testArray) {
                try {
                    int number = Integer.parseInt(s);
                    if (number < Integer.MAX_VALUE) {
                        isArrayOk = true;
                    } else {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        } else {
            isArrayOk = false;
        }
        return isArrayOk;
    }

    private Boolean checkLineCount() {
        return lineSize <= Constant.MAX_LINES;
    }

    private void runTest() {

        String array = et1.getText().toString();

        if (array.isEmpty()) {
            showMsg("Numbers array is empty");
            return;
        } else {
            testArray = array.split(" ");
        }

        if (!checkArrayLimits()) {
            showMsg("Array contains too big numbers");
        }

        try {
            lineSize = Integer.parseInt(et2.getText().toString());
        } catch (NumberFormatException e) {
            showMsg("Format error. Please input only numbers.");
            return;
        }

        if (!checkLineCount()) {
            showMsg("Line size too big. Please select a smaller number");
            return;
        }

        if (lineSize > 0) {

            createArrayFromString(array, lineSize);

        } else {
            showMsg("Line size should be > 0");
        }

    }

    private void buildMatrix(String a[][], int rows, int columns) {

        StringBuilder sb = new StringBuilder();
        /*  k - starting row index
        rows - ending row index
        l - starting column index
        columns - ending column index
        i - iterator
        */
        int i, k = 0, l = 0;

        while (k < rows && l < columns) {
            // Print the first column
            for (i = l; i < rows; i++) {
                sb.append(a[i][l]);
                sb.append(" ");
            }
            l++;

            // Print the last row
            for (i = l; i < columns; ++i) {
                sb.append(a[rows - 1][i]);
                sb.append(" ");
            }
            rows--;

            if (l < columns) {
                // Print the last column
                for (i = rows - 1; i >= k; --i) {
                    sb.append(a[i][columns - 1]);
                    sb.append(" ");
                }
                columns--;
            }

            //Print the first row
            if (k < rows) {
                for (i = columns - 1; i >= l; --i) {
                    sb.append(a[k][i]);
                    sb.append(" ");
                }
                k++;
            }
        }

        result.setText(sb.toString());

        buildExample(a);

    }

    private void buildExample(String a[][]) {

        StringBuilder ex = new StringBuilder();
        ex.append("Example");
        ex.append("\n");
        for (int r = 0; r < a.length; r++) {

            for (int c = 0; c < a[r].length; c++) {
                ex.append(" ");
                ex.append(a[r][c]);

            }
            ex.append("\n");
        }
        example.setText(ex.toString());

    }

    private void showMsg(String msg) {

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
