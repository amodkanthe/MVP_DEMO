package com.headytest.android.category_listing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.headytest.android.R;
import com.headytest.android.enities.Category;
import com.headytest.android.enities.Product;
import com.headytest.android.enities.Ranking;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryListFragment extends Fragment implements CategoryListRecyclerAdapter.CategoryClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Category> categoryList;
    List<Ranking> rankings;
    private OnFragmentInteractionListener mListener;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    CategoryListRecyclerAdapter categoryListRecyclerAdapter;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CategoryListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryListFragment newInstance(String param1, String param2) {
        CategoryListFragment fragment = new CategoryListFragment();
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
            Type listType = new TypeToken<Collection<Category>>() {
            }.getType();
            categoryList = gson.fromJson(getArguments().getString(ARG_PARAM1), listType);

            Type listTypeRanking = new TypeToken<Collection<Ranking>>() {
            }.getType();
            rankings = gson.fromJson(getArguments().getString(ARG_PARAM2), listTypeRanking);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentListComponent fragmentListComponent = DaggerFragmentListComponent.builder()
                .fragmentListModule(new FragmentListModule(getContext(), categoryList, this))
                .build();

        fragmentListComponent.inject(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvCategory);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryListRecyclerAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCategoryClicked(Category category) {
        if (mListener != null) {
            if (category.getChildCategoriesList().size() > 0) {
                mListener.onFragmentInteraction(category.getChildCategoriesList(), rankings);
            } else {
                mListener.onFragmentInteractionListProducts(category.getProducts(), rankings);
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(List<Category> categories, List<Ranking> rankings);

        void onFragmentInteractionListProducts(List<Product> products, List<Ranking> rankings);
    }
}
