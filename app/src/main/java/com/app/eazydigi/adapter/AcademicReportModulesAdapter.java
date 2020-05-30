package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnModuleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcademicReportModulesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<String> listModules = new ArrayList<>();
    List<Integer> listModuleImages = new ArrayList<>();

    OnModuleClickListener onModuleClickListener;
    private static final String TAG = "AcademicReportModulesAd";

    public AcademicReportModulesAdapter(Context c, OnModuleClickListener listener) {

        this.context = c;
        this.onModuleClickListener = listener;
    }

    public void addModules(List<String> listModule,List<Integer> listModuleImage) {

        try {

            listModules.clear();
            listModuleImages.clear();

            listModules.addAll(listModule);
            listModuleImages.addAll(listModuleImage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_module, parent, false);
        return new ModuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            ModuleViewHolder moduleViewHolder = (ModuleViewHolder) holder;

            String module = listModules.get(position);
            int icon=listModuleImages.get(position);

            moduleViewHolder.tvModule.setText("" + module);

            moduleViewHolder.ivModule.setImageResource(icon);

            moduleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onModuleClickListener != null) {
                        onModuleClickListener.onClick(module);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
//        return 8;
        return listModules.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_module)
        public CardView cvModule;

        @BindView(R.id.iv_module)
        public ImageView ivModule;

        @BindView(R.id.tv_module)
        public TextView tvModule;

        public ModuleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}