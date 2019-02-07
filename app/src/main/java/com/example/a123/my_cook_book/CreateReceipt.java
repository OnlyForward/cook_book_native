package com.example.a123.my_cook_book;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a123.my_cook_book.models.AppDbHelper;
import com.example.a123.my_cook_book.models.DbOpenHelper;
import com.example.a123.my_cook_book.models.TakeDb;
import com.example.a123.my_cook_book.modelsDb.Receipts;

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

    private int TAKE_CAMERA = 1;
    private static int element = 0;
    private Receipts mReceipts;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(RESULT_OK==resultCode){
            if(2131==requestCode){
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                ImageView img = (ImageView) manager.getChildAt(element).findViewById(R.id.takeImage);
                img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }else {
                Toast.makeText(getBaseContext(), String.valueOf(requestCode), Toast.LENGTH_LONG).show();
                Log.d("CAMERA1", "take");
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                ImageView img = (ImageView) manager.getChildAt(element).findViewById(R.id.takeImage);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.setImageBitmap(bitmap);
                Log.d("CAMERA1", "takeNoOk");
            }
        }else{

            return;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_receipt);

        mReceipts = new Receipts();

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

        manager = new LinearLayoutManager(getBaseContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
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

                mList.remove(viewHolder.getAdapterPosition());
                mList.clear();
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

        final Button btnCreate = (Button)findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbOpenHelper dbOpenHelper = TakeDb.getDbOpenHelper();
                AppDbHelper appDbHelper = new AppDbHelper(dbOpenHelper);
                mReceipts.setDescription(mDescription.getText().toString());
                mReceipts.setTitle(mTitle.getText().toString());
                //appDbHelper.saveReceipts();
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
                    element = getAdapterPosition();
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
