package com.usha.dlmachinetest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usha.dlmachinetest.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class AllMatchesFragment extends Fragment {
    RecyclerView recyclerView;
    List<MainModel.Venue> venuesArrayList;
    CustomAdapter adapter;
    DatabaseHelper db;
    TextView tvErrorMsg;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String saved;

    public AllMatchesFragment(String saved) {
        // Required empty public constructor
        this.saved = saved;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_matches, container, false);
        recyclerView = view.findViewById(R.id.recyclerVw);
        tvErrorMsg = view.findViewById(R.id.tvErrorMsg);
        db = new DatabaseHelper(requireActivity());
        if (db.isTableExists("address", true)) {

            if (saved.equals("0")) {
                if (db.getAllAddresses().size() == 0) {
                    getAllMatches();
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new CustomAdapter(db.getAllAddresses(), getActivity());
                    adapter.setOnDataChangeListener((venue, isSelected, type, position) -> {
                        db.updateAddress(venue);
                    });

                    recyclerView.setAdapter(adapter);
                }
            } else {

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new CustomAdapter(db.getAllSavedAddresses(), getActivity());
                if (db.getAllSavedAddresses().size() <= 0) {
                    tvErrorMsg.setVisibility(View.VISIBLE);
                } else {
                    tvErrorMsg.setVisibility(View.GONE);
                }
                adapter.setOnDataChangeListener((venue, isSelected, type, position) -> {
                    db.updateAddress(venue);
                });


                recyclerView.setAdapter(adapter);
            }

        }
        return view;
    }

    private void getAllMatches() {
        Call<MainModel> call = RetrofitClient.getInstance().getMyApi().
                getVenuesList("40.7484,-73.9857",
                        "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ",
                        "20180616");
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, retrofit2.Response<MainModel> response) {
                venuesArrayList = response.body().getResponse().getVenues();
                Log.d("Response: ", venuesArrayList.toString());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new CustomAdapter(venuesArrayList, getActivity());
                if (db.isTableExists("address", true)) {
                    db.addAddress(venuesArrayList);
                }

                adapter.setOnDataChangeListener((venue, isSelected, id, position) -> {
                    db.updateAddress(venue);
                });


                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.d("Error", "Response Failure");
            }
        });

    }

}
