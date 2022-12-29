package com.example.supervision_java.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.supervision_java.R;
import com.example.supervision_java.adapters.OrderAdapter;
import com.example.supervision_java.models.Order;
import com.example.supervision_java.viewmodels.OrderViewModel;
import com.example.supervision_java.views.NavigationActivity;
import com.example.supervision_java.views.activities.MainActivity;
import com.example.supervision_java.views.activities.OrderDetailActivity;

import java.util.List;

public class OrderFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private RecyclerView orderFragmentRV;
    private OrderViewModel orderViewModel;
    private OrderAdapter adapter;
    public static Context context;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        context = getActivity().getApplicationContext();
        adapter = new OrderAdapter(context);
        NavigationActivity.actionBar.setTitle("Transaksi Berlangsung");
        orderFragmentRV = view.findViewById(R.id.orderFragmentRV);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        return view;
    }

    private Observer<List<Order.Orders>> showAllOrders = new Observer<List<Order.Orders>>() {
        @Override
        public void onChanged(List<Order.Orders> orders) {
            orderFragmentRV.setLayoutManager(new LinearLayoutManager(context));
            adapter.setOrdersList(orders);
            orderFragmentRV.setAdapter(adapter);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        OrderAdapter.ordersList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        orderViewModel.getAllOrders("Bearer " + MainActivity.user.getToken());
        orderViewModel.getAllOrdersDetail().observe(getViewLifecycleOwner(), showAllOrders);
    }
}