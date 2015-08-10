package com.jwyming.service;

import com.jwyming.dao.CategoryDao;
import com.jwyming.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Created by yiming on 2015/7/30.
 */

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDao categoryDao;

    public CategoryServiceImpl() {
//        categoryDao = CategoryDao.instance;
    }

    public void createCategory(Category category) {
        categoryDao.getCategorys().put(category.getId(), category);
    }

    public Category getCategory(String id) {
        return categoryDao.getCategorys().get(id);
    }

    public Map<String, Category> getCategorys() {
        return categoryDao.getCategorys();
    }

    public List<Category> getCategoryAsList() {
        List<Category> categoryList = new ArrayList<Category>();
        Map<String, Category> categorys = categoryDao.getCategorys();
        Collection<Category> values = categorys.values();
        categoryList.addAll(values);
        return categoryList;
    }

    public int getCategorysCount() {
        return categoryDao.getCategorys().size();
    }

    public void deleteCategory(String id) {
        categoryDao.getCategorys().remove(id);
    }

}

