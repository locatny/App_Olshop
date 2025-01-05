package com.example.girlnyshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.girlnyshop.database.AppDatabase;
import com.example.girlnyshop.database.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvProducts;
    private TextView tvCari;
    private ImageView ivSelected;
    private EditText etSearch;
    private List<Integer> productImages;
    private AppDatabase productDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivSelected = view.findViewById(R.id.ivSelected);
        rvProducts = view.findViewById(R.id.rvProducts);
        tvCari = view.findViewById(R.id.tvCari);
        etSearch = view.findViewById(R.id.etSearch);

        productDatabase = AppDatabase.getDatabase(getContext());

        productImages = new ArrayList<>();
        productImages.add(R.drawable.product2);
        productImages.add(R.drawable.product1);
        productImages.add(R.drawable.product3);
        productImages.add(R.drawable.product4);
        productImages.add(R.drawable.product5);
        productImages.add(R.drawable.product6);
        productImages.add(R.drawable.product7);
        productImages.add(R.drawable.product8);
        productImages.add(R.drawable.product9);
        productImages.add(R.drawable.product10);
        productImages.add(R.drawable.product11);
        productImages.add(R.drawable.product12);
        productImages.add(R.drawable.product13);
        productImages.add(R.drawable.product14);
        productImages.add(R.drawable.product15);

        Glide.with(requireContext()).load(R.drawable.product2).into(ivSelected);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rvProducts.setLayoutManager(layoutManager);

        ProductAdapter adapter = new ProductAdapter(productImages, image -> Glide.with(requireContext()).load(image).into(ivSelected));
        rvProducts.setAdapter(adapter);

        populateCart();
    }

    private void populateCart() {
        new Thread(() -> {
            int count = productDatabase.productDao().getAllProducts().size();
            if (count == 0) {
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product1,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product2,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product3,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product4,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product5,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product6,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product7,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product8,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product9,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product10,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product11,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product12,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product13,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product14,
                                1
                        )
                );
                productDatabase.productDao().insert(
                        new Product(
                                R.drawable.product15,
                                1
                        )
                );
            }
        }).start();
        productImages = new ArrayList<>();
        productImages.add(R.drawable.product1);
        productImages.add(R.drawable.product2);
        productImages.add(R.drawable.product3);
        productImages.add(R.drawable.product4);
        productImages.add(R.drawable.product5);
        productImages.add(R.drawable.product6);
        productImages.add(R.drawable.product7);
        productImages.add(R.drawable.product8);
        productImages.add(R.drawable.product9);
        productImages.add(R.drawable.product10);
        productImages.add(R.drawable.product11);
        productImages.add(R.drawable.product12);
        productImages.add(R.drawable.product13);
        productImages.add(R.drawable.product14);
        productImages.add(R.drawable.product15);
    }

    private static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<Integer> products;
        private ProductListener productListener;

        ProductAdapter(List<Integer> products, ProductListener listener) {
            this.products = products;
            this.productListener = listener;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            Integer productImage = products.get(position);
            Glide.with(holder.itemView.getContext())
                    .load(productImage)
                    .into(holder.ivProduct);
            holder.itemView.setOnClickListener(v -> {
                productListener.onClick(productImage);
            });
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        static class ProductViewHolder extends RecyclerView.ViewHolder {

            ImageView ivProduct;

            ProductViewHolder(View itemView) {
                super(itemView);
                ivProduct = itemView.findViewById(R.id.ivProduct);
            }
        }

        public interface ProductListener {
            void onClick(Integer image);
        }
    }
}
