package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Backend_CategoryWrapper {

    @SerializedName("categories")
    @Expose

    private List<Backend_Category> backend_categories=null;

    public Backend_CategoryWrapper(List<Backend_Category> backend_categories) {
        this.backend_categories = backend_categories;
    }

    public List<Backend_Category> getBackend_categories() {
        return backend_categories;
    }

    public void setBackend_categories(List<Backend_Category> backend_categories) {
        this.backend_categories = backend_categories;
    }
}
