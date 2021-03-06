package com.example.openweathermap.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    String id;
    @SerializedName("main")
    String main;
    @SerializedName("description")
    String description;
    @SerializedName("icon")
    String icon;

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

//    @BindingAdapter({"imageUrl"})
//    public static void loadImage(final ImageView imageView, final String imageUrl) {
//        Logger.d("## imageUrl ==> ", imageUrl);
//        // 이미지는 Glide라는 라이브러리를 사용해 데이터를 설정한다
//        Glide.with(imageView.getContext())
//                .load(imageUrl)
//                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(imageView);
//    }
}
