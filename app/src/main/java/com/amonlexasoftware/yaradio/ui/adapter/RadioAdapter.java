package com.amonlexasoftware.yaradio.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amonlexasoftware.yaradio.R;
import com.amonlexasoftware.yaradio.support.service.MyMediaPlayer;
import com.amonlexasoftware.yaradio.support.Assistant;
import com.amonlexasoftware.yaradio.ui.activity.StreamRadioActivity;
import com.amonlexasoftware.yaradio.models.RadioModel;

import java.util.ArrayList;


public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    ArrayList<RadioModel> radioList;
    Context context;

    public RadioAdapter(ArrayList<RadioModel> radioList, Context context) {
        this.radioList = radioList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_radio,parent,false);
        return new RadioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RadioModel radioData = radioList.get(position);
        holder.txtNameRadio.setText(radioData.getName());
        holder.txtDescriptionRadio.setText("Канал: "+radioData.getDescription());
        Assistant.loadUrlGlide(context,radioData.getLogo(),20,holder.imgIconRadio);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    MyMediaPlayer.getInstance().reset();
                    MyMediaPlayer.currentRadio = position;
                    Intent intent = new Intent(context, StreamRadioActivity.class);
                    intent.putExtra("data",radioList);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
        });
    }



    @Override
    public int getItemCount() {
        return radioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameRadio, txtDescriptionRadio;
        ImageView imgIconRadio;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNameRadio = itemView.findViewById(R.id.txtName);
            txtDescriptionRadio = itemView.findViewById(R.id.txtDescp);
            imgIconRadio = itemView.findViewById(R.id.imgRadioIcon);
        }
    }
}
