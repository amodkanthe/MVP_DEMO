package com.headytest.android.product_listing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.headytest.android.AScope;
import com.headytest.android.R;
import com.headytest.android.enities.Product;
import com.headytest.android.enities.RankedProduct;
import com.headytest.android.enities.Ranking;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProductListingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListingFragment extends Fragment implements SortRecyclerAdapter.SortClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AlertDialog alertDialog;
    List<Product> products;
    List<Ranking> rankings;
    RecyclerView recyclerView;

    @Inject
    @AScope
    ProductsRecyclerAdapter productsRecyclerAdapter;

    @Inject
    @AScope
    LinearLayoutManager linearLayoutManager;

    public ProductListingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListingFragment newInstance(String param1, String param2) {
        ProductListingFragment fragment = new ProductListingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<Collection<Product>>() {
            }.getType();
            products = gson.fromJson(getArguments().getString(ARG_PARAM1), listType);

            Type listTypeRanking = new TypeToken<Collection<Ranking>>() {
            }.getType();
            rankings = gson.fromJson(getArguments().getString(ARG_PARAM2), listTypeRanking);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_listing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductListComponent productListComponent = DaggerProductListComponent.builder()
                .productListModule(new ProductListModule(getContext(), products))
                .build();
        productListComponent.inject(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productsRecyclerAdapter);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onSortClick(Ranking ranking) {
        alertDialog.dismiss();
        ImmutableListMultimap<Integer, RankedProduct> groupById = Multimaps.index(ranking.getRankedProducts(), BY_ID);
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                int product1Score = 0;
                int product2Score = 0;
                List<RankedProduct> rankedProducts = groupById.get(product1.getId());
                if (rankedProducts != null && rankedProducts.size() > 0) {
                    product1Score = Ranking.getScore(ranking.getRanking(), rankedProducts.get(0));
                }
                rankedProducts = groupById.get(product2.getId());
                if (rankedProducts != null && rankedProducts.size() > 0) {
                    product2Score = Ranking.getScore(ranking.getRanking(), rankedProducts.get(0));
                }
                return product1Score > product2Score ? -1 : 1;
            }
        });
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                showSortPopUp();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSortPopUp() {
        View parentView = LayoutInflater.from(getContext()).inflate(R.layout.sort_layout, null);
        alertDialog = new AlertDialog.Builder(getContext()).setView(parentView).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        RecyclerView recyclerView = (RecyclerView) alertDialog.findViewById(R.id.rvSort);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SortRecyclerAdapter(rankings, this));
    }

    private static final Function<RankedProduct, Integer> BY_ID =
            new Function<RankedProduct, Integer>() {
                @Override
                public Integer apply(RankedProduct item) {
                    return item.getId();
                }
            };
}
