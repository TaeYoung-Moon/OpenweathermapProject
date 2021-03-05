package com.example.openweathermap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweathermap.R;
import com.example.openweathermap.model.City;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 용도품 입고 화면용 리스트 어뎁터
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<City> mItems = new ArrayList<City>();

    public CityAdapter(ArrayList<City> list) {
        this.mItems = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //인플레이션을 통해 뷰 객체 만들기
        Context context = parent.getContext() ;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new ViewHolder(view); //뷰홀더 객체를 생성
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCountryCode, tvCityName;

        /**
         * 뷰 홀더를 통해 리사이클러뷰 아이템에 접근
         *
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            rowItem = itemView.findViewById(R.id.rowItem);
            tvCountryCode = itemView.findViewById(R.id.tv_country_code);
            tvCityName = itemView.findViewById(R.id.tv_city_name);

        }

        public void setItem(City item) {
            Logger.d("setItem");
            tvCountryCode.setText(item.getCountry());
            tvCityName.setText(item.getName());
        }

    }
}
