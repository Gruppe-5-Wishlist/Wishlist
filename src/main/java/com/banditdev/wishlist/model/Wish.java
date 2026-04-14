package com.banditdev.wishlist.model;

public class Wish {
    private int wishId;
    private String wishName;
    private String wishDescription;
    private String wishLink;
    private double wishPrice;


    public Wish(int wishId, String wishName, String wishDescription, String wishLink, double wishPrice) {
        this.wishId = wishId;
        this.wishName = wishName;
        this.wishDescription = wishDescription;
        this.wishLink = wishLink;
        this.wishPrice = wishPrice;
    }


    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public String getWishLink() {
        return wishLink;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "wishId=" + wishId +
                ", wishName='" + wishName + '\'' +
                ", wishDescription='" + wishDescription + '\'' +
                ", wishLink='" + wishLink + '\'' +
                ", wishPrice=" + wishPrice +
                '}';
    }
}
