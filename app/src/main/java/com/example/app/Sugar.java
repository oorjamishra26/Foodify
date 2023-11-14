package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Sugar extends AppCompatActivity {
    private final ShoppingCart shoppingCart = new ShoppingCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sugar.this, Homepage.class);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Chocolate Pancake", 295);
                shoppingCart.addItem(item);
                Toast.makeText(Sugar.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Red Velvet Pancake", 350);
                shoppingCart.addItem(item);
                Toast.makeText(Sugar.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Blueberry Pancake", 400);
                shoppingCart.addItem(item);
                Toast.makeText(Sugar.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        Button place = (Button) findViewById(R.id.addto);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
    }

    private void openCartActivity() {
        Intent i = new Intent(Sugar.this, cart.class);
        i.putParcelableArrayListExtra("cartItems", (ArrayList<CartItem>) shoppingCart.getItems());
        startActivity(i);
    }

    public static class CartItem implements Parcelable {
        private String name;
        private double price;

        public CartItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        protected CartItem(Parcel in) {
            name = in.readString();
            price = in.readDouble();
        }

        public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
            @Override
            public CartItem createFromParcel(Parcel in) {
                return new CartItem(in);
            }

            @Override
            public CartItem[] newArray(int size) {
                return new CartItem[size];
            }
        };

        public double getPrice() {
            return price;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeDouble(price);
        }

        public String getProductName() {
            return name;
        }
    }

    public class ShoppingCart {
        private List<CartItem> items = new ArrayList<>();

        public void addItem(CartItem item) {
            items.add(item);
        }

        public List<CartItem> getItems() {
            return items;
        }
    }
}