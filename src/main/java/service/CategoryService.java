package service;

import dto.CategoryDTO;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(String id);

    CategoryDTO create(CategoryDTO categoryDTO);

    void deleteById(String id);
}
