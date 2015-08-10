package com.jwyming.service;

import com.jwyming.model.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by yiming on 2015/7/31.
 */
public interface CategoryService {

    public void createCategory(Category category);

    public Category getCategory(String id);

    public Map<String, Category> getCategorys();

    public List<Category> getCategoryAsList();

    public int getCategorysCount();

    public void deleteCategory(String id);
}
