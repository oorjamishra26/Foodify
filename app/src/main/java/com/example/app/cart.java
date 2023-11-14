package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class cart extends AppCompatActivity {
    private ListView cartListView;
    private List<Sugar.CartItem> cartItems;
    private CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Button cont = (Button) findViewById(R.id.cont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cart.this, orderplaced.class);
                startActivity(i);
            }
        });

        // Retrieve the existing cartItems or initialize it if null
        if (cartItems == null) {
            cartItems = getIntent().getParcelableArrayListExtra("cartItems");
        }

        cartListView = findViewById(R.id.cartListView);

        double totalPrice = calculateTotalPrice(cartItems);
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText("" + totalPrice);

        // Create an instance of the adapter and set it for the ListView
        adapter = new CartItemAdapter(this, cartItems);
        cartListView.setAdapter(adapter);
        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cart.this, Sugar.class);
                startActivity(i);
            }
        });
    }

    public class CartItemAdapter extends BaseAdapter {
        private Context context;
        private List<Sugar.CartItem> items;

        public CartItemAdapter(Context context, List<Sugar.CartItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
            }

            Sugar.CartItem item = items.get(position);
            TextView productNameTextView = convertView.findViewById(R.id.productNameTextView);
            TextView priceTextView = convertView.findViewById(R.id.priceTextView);

            // Update the views in the layout with the item's data
            productNameTextView.setText(item.getProductName()); // Use the appropriate method to get the name
            priceTextView.setText(String.valueOf(item.getPrice()));

            return convertView;
        }
    }
    private double calculateTotalPrice(List<Sugar.CartItem> items) {
        double total = 0;
        for (Sugar.CartItem item : items) {
            total += item.getPrice() + 3;
        }
        return total;
    }

}