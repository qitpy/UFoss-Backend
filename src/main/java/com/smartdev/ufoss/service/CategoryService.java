package com.smartdev.ufoss.service;


import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.UserNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getMenu();

    String newCategory(CategoryDTO model, String parentName) throws MessageErrorException;

    String updateParentID (String name, String parent) throws UserNotFoundException;

    List<CategoryEntity> getSubCategory(String name);
}
