package com.jwyming.service;

import com.jwyming.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Created by yiming on 2015/7/31.
 */
@Path("/categorys")
@Component
public class CategorysResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @Autowired
    CategoryService categoryService;

//    public CategorysResource() {
//        categoryService = new CategoryServiceImpl();
//    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Category> getCategorys() {
        return categoryService.getCategoryAsList();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Category> getCategorysAsHtml() {
        return categoryService.getCategoryAsList();
    }

    // URI: /rest/categorys/count
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return String.valueOf(categoryService.getCategorysCount());
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createCategory(@FormParam("id") String id,
                             @FormParam("categoryname") String name,
                             @FormParam("categorytype") String type,
                             @Context HttpServletResponse servletResponse) throws IOException {
        Category category = new Category(id, name);
        categoryService.createCategory(category);
        servletResponse.sendRedirect("./categorys/");
    }

    @Path("{category}")
    public CategoryResource getCategory(@PathParam("category") String id) {
        return new CategoryResource(uriInfo, request, id);
    }

}
