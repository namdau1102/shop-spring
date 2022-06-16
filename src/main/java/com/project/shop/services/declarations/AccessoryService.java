package com.project.shop.services.declarations;

import com.project.shop.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AccessoryService {
    void save(Accessory feed, MultipartFile[] multipartFile, HttpServletRequest httpRequest) throws IOException;

    void update(Accessory accessory, MultipartFile[] files, HttpServletRequest httpRequest) throws IOException;

    void delete(Accessory accessory);

    Optional<Accessory> getAccessory(long id);

    Page<Accessory> getAllAccessories(Pageable pageable, SearchForm searchForm);

    List<Type> getAllAccessoryTypes();

    void addToCart(CartItem accessory, Accessory item);

    Page<Accessory> getAccessoryByType(Type type, Pageable pageable);

}
