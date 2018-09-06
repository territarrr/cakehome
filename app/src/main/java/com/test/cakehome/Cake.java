package com.test.cakehome;

/**
 * Created by Sovochka on 05.09.2018.
 */

public class Cake implements IItem {
    private String name;
    private String desc;
    private int image;
    private String review;

    public Cake(String name, String desc, int image, String review) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int getViewType() {
        return 1;
    }
}
