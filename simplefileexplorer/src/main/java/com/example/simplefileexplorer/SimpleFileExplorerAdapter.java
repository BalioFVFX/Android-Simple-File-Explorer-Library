package com.example.simplefileexplorer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class SimpleFileExplorerAdapter extends RecyclerView.Adapter<SimpleFileExplorerViewHolder> {

    private Context context;
    private List<FileModel> filesList;
    private AdapterListener adapterListener;
    private int previousItemSelectedIndex;

    public SimpleFileExplorerAdapter(Context context) {
        this.context = context;
        this.filesList = new ArrayList<>();
        this.previousItemSelectedIndex = -1;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }


    @NonNull
    @Override
    public SimpleFileExplorerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View fileExplorerListView = layoutInflater.inflate(R.layout.file_explorer_list, null, false);

        return new SimpleFileExplorerViewHolder(fileExplorerListView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleFileExplorerViewHolder simpleFileExplorerViewHolder, final int i) {
        FileModel fileModel = this.filesList.get(i);

        simpleFileExplorerViewHolder.backgroundConstraintLayout.setBackgroundColor(Color.rgb(255, 255, 255));
        this.setTextByFileAbsolutePath(simpleFileExplorerViewHolder.fileAbsolutePathTextView, fileModel.getAbsolutePath());
        this.setImagesByFileType(simpleFileExplorerViewHolder.fileImageView, fileModel.getFileModelType());
        this.setLayoutOnClickListenerByFileType(simpleFileExplorerViewHolder.backgroundConstraintLayout, fileModel.getFileModelType(), fileModel.getAbsolutePath(), i);
        this.updateSelectedItemColor(simpleFileExplorerViewHolder.backgroundConstraintLayout, i);


    }

    @Override
    public int getItemCount() {
        return this.filesList.size();
    }

    public void loadDirectory(List<FileModel> filesList) {
        this.filesList = new ArrayList<>(filesList);
        this.notifyDataSetChanged();
    }

    private void setTextByFileAbsolutePath(TextView textView, String absolutePath) {
        textView.setText(absolutePath);
    }

    private void setImagesByFileType(ImageView imageView, FileModelType fileModelType) {
        int fileImageId = 0;
        int directoryImageId = 0;
        if(SimpleFileResources.imageFileId == null){
            directoryImageId = SimpleFileResources.defaultImageFileId;
        }
        if(SimpleFileResources.imageDirectoryId == null){
            directoryImageId = SimpleFileResources.defaultImageDirectoryId;
        }
        switch (fileModelType) {
            case FILE:
                imageView.setImageResource(fileImageId);
                break;
            case DIRECTORY:
                imageView.setImageResource(directoryImageId);
                break;
            default:
                break;
        }
    }

    private void setLayoutOnClickListenerByFileType(final ConstraintLayout layout, final FileModelType fileModelType, final String absolutePath, final int index) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (fileModelType) {
                    case FILE:
                        adapterListener.onFileClick(absolutePath);
                        previousItemSelectedIndex = index;
                        notifyDataSetChanged();
                        break;
                    case DIRECTORY:
                        adapterListener.onDirectoryClick(absolutePath);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void updateSelectedItemColor(ConstraintLayout layout, final int index) {
        if (this.previousItemSelectedIndex == index) {
            layout.setBackgroundColor(Color.rgb(168, 168, 168));
            this.previousItemSelectedIndex = -1;
        }
    }
}
