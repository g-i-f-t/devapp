package com.example.activity_maim.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.activity_maim.Data;
import com.example.activity_maim.R;

import java.util.ArrayList;

public class Funding_list_adapter1 extends RecyclerView.Adapter<Funding_list_adapter1.ItemViewHolder> {

        // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();
    private Funding_list_adapter.Callback callback;

    public Funding_list_adapter1(Funding_list_adapter.Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onCallback();
            }
        });
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    public interface Callback {
        public void onCallback();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        private LinearLayout linearLayout;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.list_item_layout);
        }

        void onBind(Data data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());

//            itemView.setOnClickListener((View.OnClickListener) this);
//            textView1.setOnClickListener((View.OnClickListener) this);
//            textView2.setOnClickListener((View.OnClickListener) this);
//            imageView.setOnClickListener((View.OnClickListener) this);
        }
    }
}

/* public class Funding_list_adapter extends RecyclerView.Adapter<Funding_list_adapter.ViewHolder> {
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
*/
