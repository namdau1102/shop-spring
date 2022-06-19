package com.project.shop.services.declarations;

import java.util.List;
import java.util.Optional;

import com.project.shop.models.Type;


public interface CategorieService {
      List<Type> findAll();
      Optional<Type> getTypeById(Long id);
      
      void save(Type type);
      
      void update(Type type,Long id);
      
      void delete(Long id);
}
