package com.example.openweathermap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweathermap.Listener.OnItemClickListener;
import com.example.openweathermap.R;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.model.City;
import com.example.openweathermap.viewmodel.MainViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 용도품 입고 화면용 리스트 어뎁터
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private ArrayList<City> mItems = new ArrayList<City>();
    private OnItemClickListener mOnItemClickListener;

    public CityAdapter(ArrayList<City> list, OnItemClickListener onItemClickListener) {
        this.mItems = list;
        this.mOnItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //인플레이션을 통해 뷰 객체 만들기
        Context context = parent.getContext() ;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new ViewHolder(view, mOnItemClickListener); //뷰홀더 객체를 생성
    }


    /**
     * 아이템을 뷰 홀더에 바인딩
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        City item = mItems.get(position);


        //뷰 홀더에 아이템 전달
        viewHolder.setItem(item);
    }

    /**
     * 아이템 수 반환
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return this.mItems.size();
    }


    /**
     * 아이템 뷰를 저장하는 뷰홀더 클래스.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvCountryCode, tvCityName;
        private OnItemClickListener onItemClickListener;
        private int mPosition;
        private City mCity;

        /**
         * 뷰 홀더를 통해 리사이클러뷰 아이템에 접근
         *
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

//            rowItem = itemView.findViewById(R.id.rowItem);
            tvCountryCode = itemView.findViewById(R.id.tv_country_code);
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);

        }

        public void setItem(City item) {
            mPosition = getAdapterPosition();
            mCity = mItems.get(mPosition);

            Logger.d("setItem");
            tvCountryCode.setText(item.getCountry());
            tvCityName.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(mCity.getId());

        }
    }
}
