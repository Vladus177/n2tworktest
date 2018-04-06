package ru.vladus177.n3tworktest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


public class ItemThreeFragment extends Fragment implements View.OnClickListener {

    Button btn1, btn2;
    RandCircle result;
    ArrayList<SimpleCircle> circles = new ArrayList<>();

    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() != null) {

            result = getView().findViewById(R.id.circles);
            btn1 = getView().findViewById(R.id.btn3_run_coll);
            btn1.setOnClickListener(this);
            btn2 = getView().findViewById(R.id.btn3_run_safe);
            btn2.setOnClickListener(this);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_three, container, false);
    }

    private void showResult(boolean collide) {

        circles.clear();

        redraw(collide);

        result.setDataList(circles);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn3_run_coll: {
                showResult(true);
                break;
            }

            case R.id.btn3_run_safe: {
                showResult(false);
                break;
            }
        }
    }

    public void redraw(boolean colliding) {

        Random random = new Random();

        int counter = 0;

        while (counter < 5) {
            int size = 20 + random.nextInt(30);
            //int randomNumber = random.nextInt(max + 1 - min) + min;

            int maxWidth = result.getWidth() - (40);
            int maxHeight = result.getHeight() - (40);
            int x = (size + 1) + random.nextInt(maxWidth - 1);
            int y = (size + 1) + random.nextInt(maxHeight - 1);

            if (counter == 0) {

                addCircle(x, y, size);
                counter++;

            } else {

                if (colliding) {

                    if (hasCollision(x, y, size)) {
                        addCircle(x, y, size);
                        counter++;
                    }

                } else {

                    if (!hasCollision(x, y, size)) {
                        addCircle(x, y, size);
                        counter++;
                    }

                }
            }
        }
    }

    public boolean hasCollision(float x, float y, int size) {

        for (SimpleCircle data2 : circles) {
            float xDif = x - data2.x;
            float yDif = y - data2.x;
            float distanceSquared = xDif * xDif + yDif * yDif;
            boolean collision = distanceSquared < ((size + Constant.STROKE_WIDTH + 1) +
                    (data2.size + Constant.STROKE_WIDTH + 1) * ((size + Constant.STROKE_WIDTH + 1)
                            + (data2.size + Constant.STROKE_WIDTH + 1)));

            if (collision) {
                return true;
            }
        }

        return false;
    }

    private void addCircle(int x, int y, int size) {
        circles.add(new SimpleCircle(x, y, size));
    }


}
