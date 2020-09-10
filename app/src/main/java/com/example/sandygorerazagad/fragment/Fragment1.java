package com.example.sandygorerazagad.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sandygorerazagad.R;
import com.example.sandygorerazagad.adapter.ItemAdapter;
import com.example.sandygorerazagad.interfaces.NetworkResponseListener;
import com.example.sandygorerazagad.model.ItemModel;
import com.example.sandygorerazagad.task.LoadDataTopLearnersTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment implements NetworkResponseListener {

    private RecyclerView itemlist;
    private ProgressBar progressBar;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemlist=view.findViewById(R.id.item_list);
        progressBar=view.findViewById(R.id.progress);
         LoadDataTopLearnersTask loadDataTopLearnersTask =new LoadDataTopLearnersTask(Fragment1.this);
        loadDataTopLearnersTask.execute();
    }

    @Override
    public void SuccessData(String data) {
        progressBar.setVisibility(View.GONE);
       // textView.setText("Added From Fragment 1 : "+data);
        List<ItemModel> itemModels=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                itemModels.add(new ItemModel(jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("hours"),jsonArray.getJSONObject(i).getString("country"),jsonArray.getJSONObject(i).getString("badgeUrl")));
            }

            ItemAdapter itemAdapter=new ItemAdapter(getContext(),itemModels);
            itemlist.setLayoutManager(new LinearLayoutManager(getContext()));
            itemlist.setAdapter(itemAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to Parse Data", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void FailedData() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to Load Data on Fragment 1", Toast.LENGTH_SHORT).show();

    }
}
