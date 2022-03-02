package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.database.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new MyDatabase(this);

        binding.btnSave.setOnClickListener(v -> {
            String model = binding.etModel.getText().toString().trim();
            String color = binding.etColor.getText().toString().trim();
            double dpl = Double.parseDouble(binding.etDpl.getText().toString().trim());


            Car car = new Car(model, color, dpl);



            boolean res = db.insertCar(car);
            if (res)
                Toast.makeText(this, "Insertion Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "The model is exist", Toast.LENGTH_SHORT).show();
        });

        binding.btnRestore.setOnClickListener(v -> {
            db = new MyDatabase(this);
//            long num = db.countCars();
            ArrayList<Car> cars = db.getCarById(1+"");
            for (Car c : cars){
                Toast.makeText(this, "" +c.getColor() + c.getModel(), Toast.LENGTH_SHORT).show();
                Log.d("CAR color", c.getColor());
            }
        });
        binding.btnDelete.setOnClickListener(v -> {
            String model = binding.etModel.getText().toString().trim();
            String color = binding.etColor.getText().toString().trim();
            double dpl = Double.parseDouble(binding.etDpl.getText().toString().trim());

            Car car = new Car(model, color, dpl);
            boolean res = db.deleteCar(car);
            if (res)
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
        });
    }
}