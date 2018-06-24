package com.headytest.android.category_listing;

import com.headytest.android.AScope;
import com.headytest.android.enities.Result;

/**
 * Created by amod on 6/14/2018.
 */
@AScope
public interface CategoryContract {

    @AScope
    public interface CategoryPresenter {

        public void onStart();

        public void onStop();

        public void getCategoryLiast();

        public void setView(CategoryView categoryView);

    }

    @AScope
    public interface CategoryView {

        public void onPreAPIRequest();

        public void onAPISuccess();

        public void onAPIError();

        public void setCategoryList(Result result);
    }
}
