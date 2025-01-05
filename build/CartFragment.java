package com.example.girlnyshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.girlnyshop.database.AppDatabase;
import com.example.girlnyshop.database.Product;

import java.util.List;

public class CartFragment extends Fragment {

    private TextView tvTitle;
    private RecyclerView rvProducts;
    private ImageView ivBack, ivCart;
    private ProductAdapter adapter;
    private List<Product> productList;
    private AppDatabase productDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        tvTitle = view.findViewById(R.id.tvTitle);
        ivBack = view.findViewById(R.id.ivBack);
        ivCart = view.findViewById(R.id.ivCart);
        rvProducts = view.findViewById(R.id.rvProducts);

        productDatabase = AppDatabase.getDatabase(getContext());

        ivBack.setOnClickListener(v -> getActivity().onBackPressed());

        ivCart.setOnClickListener(v -> {

        });

        loadProducts();

        return view;
    }

    private void loadProducts() {
        new Thread(() -> {
            productList = productDatabase.productDao().getAllProducts();

            getActivity().runOnUiThread(() -> {
                adapter = new ProductAdapter(productList);
                rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
                rvProducts.setAdapter(adapter);
            });
        }).start();
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<Product> productList;

        ProductAdapter(List<Product> productList) {
            this.productList = productList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            Product product = productList.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        class ProductViewHolder extends RecyclerView.ViewHolder {

            ImageView ivProductImage;
            TextView tvQuantity;
            ImageView ivIncrease, ivDecrease;

            ProductViewHolder(View itemView) {
                super(itemView);
                ivProductImage = itemView.findViewById(R.id.ivProductImage);
                tvQuantity = itemView.findViewById(R.id.tvQuantity);
                ivIncrease = itemView.findViewById(R.id.ivIncrease);
                ivDecrease = itemView.findViewById(R.id.ivDecrease);
            }

            void bind(Product product) {
                Glide.with(itemView.getContext()).load(product.getImageResource()).into(ivProductImage);
                tvQuantity.setText(String.valueOf(product.getQuantity()));

                ivIncrease.setOnClickListener(v -> {
                    product.setQuantity(product.getQuantity() + 1);
                    updateProductInDatabase(product);
                    notifyDataSetChanged();
                });

                ivDecrease.setOnClickListener(v -> {
                    if (product.getQuantity() > 1) {
                        product.setQuantity(product.getQuantity() - 1);
                        updateProductInDatabase(product);
                        notifyDataSetChanged();
                    }
                });
            }

            private void updateProductInDatabase(Product product) {
                new Thread(() -> productDatabase.productDao().update(product)).start();
            }
        }
    }
}
