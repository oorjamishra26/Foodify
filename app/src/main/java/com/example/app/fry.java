package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fry extends AppCompatActivity {
    private final ShoppingCart shoppingCart = new ShoppingCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fry);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(fry.this, Homepage.class);
                startActivity(i);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sugar.CartItem item = new Sugar.CartItem("Peri Peri Fries", 295);
                shoppingCart.addItem(item);
                Toast.makeText(fry.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sugar.CartItem item = new Sugar.CartItem("Cheese Burger", 350);
                shoppingCart.addItem(item);
                Toast.makeText(fry.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sugar.CartItem item = new Sugar.CartItem("Berry Blast Smoothie", 389);
                shoppingCart.addItem(item);
                Toast.makeText(fry.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        Button place = (Button) findViewById(R.id.addt);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
    }

    private void openCartActivity() {
        Intent i = new Intent(fry.this, cart.class);
        i.putParcelableArrayListExtra("cartItems", (ArrayList<Sugar.CartItem>) shoppingCart.getItems());
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

        public static final Creator<Sugar.CartItem> CREATOR = new Creator<Sugar.CartItem>() {
            @Override
            public Sugar.CartItem createFromParcel(Parcel in) {
                return new Sugar.CartItem(in);
            }

            @Override
            public Sugar.CartItem[] newArray(int size) {
                return new Sugar.CartItem[size];
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
        private List<Sugar.CartItem> items = new ArrayList<>();

        public void addItem(Sugar.CartItem item) {
            items.add(item);
        }

        public List<Sugar.CartItem> getItems() {
            return items;
        }
    }
}
