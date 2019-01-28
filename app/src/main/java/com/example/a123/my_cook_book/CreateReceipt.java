package com.example.a123.my_cook_book;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CreateReceipt extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextInputEditText mTitle;
    private TextInputEditText mDescription;
    private Button btnAdd;
    private Button btnReduce;

    private LinearLayoutManager manager;
    private StepsAdapter adapter;
    private List<String> mList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_receipt);

        mList = new ArrayList<>();

        mTitle = (TextInputEditText)findViewById(R.id.edit_title);
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDescription = (TextInputEditText)findViewById(R.id.description_image);
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        manager = new LinearLayoutManager(getBaseContext());
        adapter = new StepsAdapter();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_create);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        final ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {

                // Do Stuff
                mList.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();

            }

        });
        swipeToDismissTouchHelper.attachToRecyclerView(mRecyclerView);

        btnAdd = (Button)findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.add("");
                adapter.notifyDataSetChanged();
            }
        });

        btnReduce = (Button)findViewById(R.id.btn_reduce);
        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(mList.size()-1);
                adapter.notifyDataSetChanged();
            }
        });
    }


    private class StepsHolder extends RecyclerView.ViewHolder{

        private ImageView mImage;
        private TextInputEditText mDescription;

        public StepsHolder(@NonNull View itemView) {
            super(itemView);
            mDescription = (TextInputEditText)itemView.findViewById(R.id.description_image);

            mImage = (ImageView)itemView.findViewById(R.id.takeImage);
            mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    BottomSheetFrag dialog = new BottomSheetFrag();
                    dialog.show(getSupportFragmentManager(), dialog.getTag());

                }
            });
        }
    }

    private class StepsAdapter extends RecyclerView.Adapter<StepsHolder>{

        @NonNull
        @Override
        public StepsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new StepsHolder(getLayoutInflater().inflate(R.layout.item_create,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull StepsHolder stepsHolder, int i) {
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
