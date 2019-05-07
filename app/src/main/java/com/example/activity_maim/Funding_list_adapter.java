package com.example.activity_maim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Funding_list_adapter extends RecyclerView.Adapter<Funding_list_adapter.ViewHolder> {
    private final LayoutInflater inflater;

    ArrayList<String> _title;
    public Funding_list_adapter(Context context,ArrayList<String> _title){
        inflater=LayoutInflater.from(context);
        this._title=_title;
    }

    @NonNull
    @Override
    public Funding_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.list_row,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Funding_list_adapter.ViewHolder viewHolder, int i) {

        viewHolder.title.setText(_title.get(i));
    }

    @Override
    public int getItemCount() {
        return _title.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);

        }
    }
}
