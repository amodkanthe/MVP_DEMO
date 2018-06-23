
package com.headytest.android.enities;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {

    public static final String RANKING_TYPE_VIEWED = "Most Viewed Products";
    public static final String RANKING_TYPE_ORDERED = "Most OrdeRed Products";
    public static final String RANKING_TYPE_SHARED = "Most ShaRed Products";
    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private List<RankedProduct> rankedProducts = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<RankedProduct> getRankedProducts() {
        return rankedProducts;
    }

    public void setRankedProducts(List<RankedProduct> rankedProducts) {
        this.rankedProducts = rankedProducts;
    }

    public static int getScore(String type, RankedProduct rankedProduct) {
        int score = 0;
        if (type.equalsIgnoreCase(RANKING_TYPE_VIEWED)) {
            score = rankedProduct.getViewCount();
        } else if (type.equalsIgnoreCase(RANKING_TYPE_ORDERED)) {
            score = rankedProduct.getOrderCount();
        } else if (type.equalsIgnoreCase(RANKING_TYPE_SHARED)) {
            score = rankedProduct.getShares();
        }
        return score;
    }
}
