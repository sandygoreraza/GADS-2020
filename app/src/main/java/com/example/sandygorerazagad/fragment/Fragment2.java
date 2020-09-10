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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sandygorerazagad.R;
import com.example.sandygorerazagad.adapter.SkillsIQAdapter;
import com.example.sandygorerazagad.interfaces.NetworkResponseListener;
import com.example.sandygorerazagad.model.RetroSkilliq;
import com.example.sandygorerazagad.task.LoadDataSkillsIOTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment implements NetworkResponseListener {


    private RecyclerView itemlist;
    private ProgressBar progressBar;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemlist=view.findViewById(R.id.list_item2);
        //Now Lets Show a Progressbar
        progressBar=view.findViewById(R.id.progress2);
        LoadDataSkillsIOTask loadDataskillsTask=new LoadDataSkillsIOTask(Fragment2.this);
        loadDataskillsTask.execute();
    }

    @Override
    public void SuccessData(String data) {
        progressBar.setVisibility(View.GONE);

        try {
            List<RetroSkilliq> RetroSkilliqs=new ArrayList<>();
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                RetroSkilliqs.add(new RetroSkilliq(jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("score"),jsonArray.getJSONObject(i).getString("country"),jsonArray.getJSONObject(i).getString("badgeUrl")));
            }

            SkillsIQAdapter SkillsIQAdapter=new SkillsIQAdapter(getContext(),RetroSkilliqs);
            itemlist.setLayoutManager(new LinearLayoutManager(getContext()));
            itemlist.setAdapter(SkillsIQAdapter);
        }
        catch (JSONException e){
            e.printStackTrace();
        }



    }

    @Override
    public void FailedData() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Failed to Load Data on Fragment 2", Toast.LENGTH_SHORT).show();

    }
}
