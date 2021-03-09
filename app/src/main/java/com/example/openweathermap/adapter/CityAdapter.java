package com.example.openweathermap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweathermap.R;
import com.example.openweathermap.listener.OnItemClickListener;
import com.example.openweathermap.model.City;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;

/**
 * 용도품 입고 화면용 리스트 어뎁터
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements Filterable, FastScrollRecyclerView.SectionedAdapter {

    private ArrayList<City> mItems;
    private ArrayList<City> mFilteredItems;
    private OnItemClickListener mOnItemClickListener;

    public CityAdapter(ArrayList<City> list, OnItemClickListener onItemClickListener) {
        this.mItems = list;
        this.mFilteredItems = list;
        this.mOnItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //인플레이션을 통해 뷰 객체 만들기
        Context context = parent.getContext();

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
        City item = mFilteredItems.get(position);


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
        return this.mFilteredItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredItems = mItems;
                } else {
                    ArrayList<City> filteringList = new ArrayList<>();
                    for (City city : mItems) {
                        if (city.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(city);
                        }
                    }
                    mFilteredItems = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredItems;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredItems = (ArrayList<City>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        if (mFilteredItems.get(position).getCountry().isEmpty()) {
            return String.valueOf(mFilteredItems.get(position).getCountry());
        } else {
            return String.valueOf(mFilteredItems.get(position).getCountry().charAt(0));
        }
    }


    /**
     * 아이템 뷰를 저장하는 뷰홀더 클래스.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            tvCountryCode = itemView.findViewById(R.id.tv_country_code);
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);

        }

        public void setItem(City item) {
            mPosition = getAdapterPosition();
            mCity = mFilteredItems.get(mPosition);
            tvCountryCode.setText(item.getCountryName(item.getCountry()));
            tvCityName.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(mCity.getId());
        }
    }
}

