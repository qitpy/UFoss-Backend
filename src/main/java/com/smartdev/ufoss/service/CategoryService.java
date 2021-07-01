package com.smartdev.ufoss.service;


import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.exception.MessageErrorException;
import com.smartdev.ufoss.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getMenu();

    CategoryDTO newCategory(CategoryDTO model) throws MessageErrorException;

    String updateParentID (String name, String parent) throws NotFoundException;
}
