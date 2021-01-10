package com.mahan.memorybucket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    LayoutInflater layoutInflater;

    ArrayList<ConstraintLayout> memoryLayouts;
    ArrayList<Memory> memoryObjs;
    int[] imageIDs = {R.id.star0,R.id.star1,R.id.star2,R.id.star3,R.id.star4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        layoutInflater = getLayoutInflater();


        final ConstraintLayout memoryLayout = findViewById(R.id.memory1);








    }

    public void addMemory(Memory memory){
        final ConstraintLayout layout = (ConstraintLayout) layoutInflater.inflate(R.layout.memory_layout,null);

        EditText dateView = layout.findViewById(R.id.dateViewEditor);
        dateView.setText(memory.dateString);

        EditText contentView = layout.findViewById(R.id.contentView);
        contentView.setText(memory.content);


        final int pos = memoryLayouts.size();

        layout.findViewById(R.id.star0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarts(layout,1);
            }
        });

        layout.findViewById(R.id.star1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarts(layout,2);
            }
        });

        layout.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarts(layout,3);
            }
        });

        layout.findViewById(R.id.star3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarts(layout,4);
            }
        });

        layout.findViewById(R.id.star4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarts(layout,5);
            }
        });

        layout.findViewById(R.id.expandButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView contentView = layout.findViewById(R.id.contentView);
                if(contentView.getMaxHeight() == ((int) convertDpToPixel(100,context))){
                    v.animate().rotation(180).setDuration(500).start();
                    contentView.setMaxHeight((int) convertDpToPixel(1000,context));
                }
                else {
                    v.animate().rotation(0).setDuration(500).start();
                    contentView.setMaxHeight((int) convertDpToPixel(100,context));
                }
            }
        });

        layout.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memoryObjs.get(pos - memoryObjs.size() - 1).isEditing){
                    editMemory();

                }
                else
            }
        });

        memoryObjs.add(0,memory);
        memoryLayouts.add(0,layout);
        lockMemory(layout);

    }



    private void setStarts(ConstraintLayout layout, int rating){
        for (int i = 0; i < rating; i++) {
            ImageView imageView = layout.findViewById(imageIDs[i]);
            imageView.setImageResource(R.drawable.ic_star_selected);
        }
        for (int i = rating; i < 5; i++) {
            ImageView imageView = layout.findViewById(imageIDs[i]);
            imageView.setImageResource(R.drawable.ic_star_unselected);
        }
    }

    public void onMemoryClick(View view){

    }

    public void lockMemory(ConstraintLayout memoryLayout){
        memoryObjs.get()
        EditText editText = memoryLayout.findViewById(R.id.dateViewEditor);

        editText.setCursorVisible(false);
        editText.setLongClickable(false);
        editText.setClickable(false);
        editText.setFocusable(false);
        editText.setSelected(false);
        editText.setKeyListener(null);
        editText.setBackgroundResource(android.R.color.transparent);
    }

    public void editMemory(int pos){
        memoryObjs.get(pos).isEditing = true;
        ConstraintLayout memoryLayout = memoryLayouts.get(pos);

        EditText editText = memoryLayout.findViewById(R.id.dateViewEditor);

        editText.setCursorVisible(true);
        editText.setLongClickable(true);
        editText.setClickable(true);
        editText.setFocusable(true);
        editText.setSelected(true);
        editText.setKeyListener(null);
        editText.setBackgroundResource(android.R.color.transparent);
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}