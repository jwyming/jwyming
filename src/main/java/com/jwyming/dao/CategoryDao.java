package com.jwyming.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.google.common.base.Strings;
import com.jwyming.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	public Map<String, Category> getCategorys() {
		System.out.println("in CategoryDao.getCategorys()...");
		String sql = " select * from categories order by id ";
		Map<String, String> paramMap = new HashMap<>();
//		if (!Strings.isNullOrEmpty(categoryId)) {
//			sql += " and id = :categoryId ";
//			paramMap.put("categoryId", categoryId);
//		}
//		if (!Strings.isNullOrEmpty(categoryId)) {
//			sql += " and name = :categoryName ";
//			paramMap.put("categoryName", categoryName);
//		}
//		sql += " order by id ";

		List<Category> categoryList =  namedParameterJdbcTemplate.query(sql, paramMap, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int i) throws SQLException {
				Category category = new Category();
				category.setId(rs.getString(1));
				category.setName(rs.getString(2));
				return category;
			}
		});
		System.out.println("categoryList.size() =" + categoryList.size());
		Map<String, Category> map = new HashMap<>();
		for(Category category : categoryList) {
			map.put(category.getId(), category);
		}
		return map;
	}
}
