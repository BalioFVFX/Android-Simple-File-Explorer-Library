package com.example.simplefileexplorer;


import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SimpleFileExplorerFragment extends Fragment implements AdapterListener {

    private RecyclerView recyclerView;
    private SimpleFileExplorerAdapter fileExplorerAdapter;
    private String selectedAbsolutePath;
    private ActivityListener activityListener;

    public SimpleFileExplorerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple_file_explorer, container, false);

        this.initViews(view);
        this.loadDirectory();

        return view;
    }

    private void initRecyclerView(View view) {
        this.recyclerView = view.findViewById(R.id.recycler_file_explorer);
        this.fileExplorerAdapter = new SimpleFileExplorerAdapter(getContext());
        this.fileExplorerAdapter.setAdapterListener(this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.fileExplorerAdapter);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void loadDirectory() {
        File root = Environment.getExternalStorageDirectory();

        if (this.selectedAbsolutePath != null) {
            root = new File(this.selectedAbsolutePath);
        } else {
            this.selectedAbsolutePath = root.getAbsolutePath();
        }

        List<FileModel> fileModelList = new ArrayList<>();


        final File[] listFiles = root.listFiles();
        if (root.isDirectory() && listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    fileModelList.add(new FileModel(file.getAbsolutePath(), FileModelType.DIRECTORY));
                } else {
                    final File parent = file.getParentFile();
                    if (parent == null) {
                        continue;
                    }
                    fileModelList.add(new FileModel(file.getAbsolutePath(), parent.getAbsolutePath(), FileModelType.FILE));
                }
            }
        } else {
            fileModelList.add(new FileModel(root.getAbsolutePath(), FileModelType.DIRECTORY));
        }

        this.updateRecyclerList(fileModelList);
    }

    private void updateRecyclerList(List<FileModel> fileModels) {
        this.fileExplorerAdapter.loadDirectory(fileModels);
    }


    private void initViews(View view) {
        this.initRecyclerView(view);
    }

    @Override
    public void onDirectoryClick(String selectedAbsolutePath) {
        this.activityListener.onDirectoryChanged(selectedAbsolutePath);
    }

    @Override
    public void onFileClick(FileModel fileModel) {
        this.activityListener.onFileSelect(fileModel);
    }

    void setListeners(ActivityListener activityListener) {
        this.activityListener = activityListener;
    }

    void setDirectory(String dir) {
        this.selectedAbsolutePath = dir;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.activityListener.onBackButtonPressed(this.selectedAbsolutePath);
    }
}
