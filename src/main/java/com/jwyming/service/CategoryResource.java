package com.jwyming.service;

import com.jwyming.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * Created by yiming on 2015/7/31.
 */
@Component
public class CategoryResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    @Autowired
    CategoryService categoryService;


    public CategoryResource() {
    }

    public CategoryResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
//        categoryService = new CategoryServiceImpl();
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Category getCategory() {
        Category category = categoryService.getCategory(id);
        return category;
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public Category getCategoryAsHtml() {
        Category category = categoryService.getCategory(id);
        return category;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putCategory(JAXBElement<Category> categoryElement) {
        Category category = categoryElement.getValue();
        Response response;
        if (categoryService.getCategorys().containsKey(category.getId())) {
            response = Response.noContent().build();
        } else {
            response = Response.created(uriInfo.getAbsolutePath()).build();
        }
        categoryService.createCategory(category);
        return response;
    }

    @DELETE
    public void deleteCategory() {
        categoryService.deleteCategory(id);
    }

}
