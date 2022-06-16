package com.project.shop.services.declarations;

import com.project.shop.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface PetService {
    void save(Pet pet, MultipartFile[] multipartFile, HttpServletRequest httpRequest) throws IOException;

    void update(Pet pet, MultipartFile[] files, HttpServletRequest httpRequest) throws IOException;

    void delete(Pet pet);

    Optional<Pet> getAnimal(long id);

    Page<Pet> getAllPets(Pageable pageable, SearchForm searchForm);
    
    List<Type> getAllPetTypes();

    void addToCart(CartItem pet);
    
    List<Pet> getAllPetValue();
    
    Page<Pet> getAnimalByType(Type type, Pageable pageable);

}
