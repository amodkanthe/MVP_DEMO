
package com.headytest.android.enities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {

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

}
