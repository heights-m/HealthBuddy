package com.example.mainactivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.databinding.FoodListItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FoodListRecAdapter extends RecyclerView.Adapter<FoodListRecAdapter.ViewHolder> {

    private ArrayList<IntakeInput> mealIntakeArray = new ArrayList<>();
    private ViewGroup pContext;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        pContext = parent;
        return new ViewHolder(FoodListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodListRecAdapter.ViewHolder holder, int position) {
        IntakeInput foodItem = mealIntakeArray.get(position); //gets the foodItem in the position index of the ArrayList
        //initialising info of food
        holder.binding.listItemName.setText(foodItem.getName());
        holder.binding.listKcalAmt.setText(Integer.toString(foodItem.getKcal()) + " kcal / " + Integer.toString(foodItem.getFoodAmt()) + " " + foodItem.getFoodUnit()); //TODO: change foodUnit values to proper strings
        Log.i("LOG_TAG", "Food Name: " + foodItem.getName());


//        holder.binding.itemDeleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: delete database food array item when clicked
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mealIntakeArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FoodListItemBinding binding;
        private ImageView editBtn, deleteBtn;
        public ViewHolder(@NonNull @NotNull FoodListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            editBtn = binding.itemEditBtn;
            deleteBtn = binding.itemDeleteBtn;

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(pContext.getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
                    DatabaseHandler db = new DatabaseHandler(pContext.getContext());
                    db.deleteIntake(mealIntakeArray.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });

        }
    }

    public void setMealIntakeArray(ArrayList<IntakeInput> mealIntakeArray) {
        this.mealIntakeArray = mealIntakeArray;
        notifyDataSetChanged();
    }

}
