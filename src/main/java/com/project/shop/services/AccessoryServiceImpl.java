package com.project.shop.services;

import com.project.shop.models.Accessory;
import com.project.shop.models.CartItem;
import com.project.shop.models.SearchForm;
import com.project.shop.models.Type;
import com.project.shop.repositories.AccessoryRepository;
import com.project.shop.repositories.FeedRepository;
import com.project.shop.repositories.TypeRepository;
import com.project.shop.services.declarations.AccessoryService;
import com.project.shop.services.declarations.FileService;
import com.project.shop.services.declarations.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.projection.Accessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {
    private final AccessoryRepository accessoryRepository;

    private final TypeRepository typeRepository;

    private final ShoppingService shoppingService;

    private final FileService fileService;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository, TypeRepository typeRepository, ShoppingService shoppingService, FileService fileService) {
        this.accessoryRepository = accessoryRepository;
        this.typeRepository = typeRepository;
        this.shoppingService = shoppingService;
        this.fileService = fileService;
    }


    @Override
    public void save(Accessory accessory, MultipartFile[] multipartFile, HttpServletRequest httpRequest) throws IOException {
        for (MultipartFile file : multipartFile)
            fileService.save(file, httpRequest);

        List<String> images = Arrays.stream(multipartFile)
                .filter(e -> !e.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .map(e -> "/uploads/" + e)
                .collect(Collectors.toList());

        accessory.setImages(images);
        accessoryRepository.saveAndFlush(accessory);
    }


    @Override
    public void update(Accessory accessory, MultipartFile[] files, HttpServletRequest httpRequest) throws IOException {
        save(accessory, files, httpRequest);
    }

    @Override
    public void delete(Accessory accessory) {
        accessory.setDeleted(Boolean.TRUE);
        accessoryRepository.save(accessory);
    }


    @Override
    public Optional<Accessory> getAccessory(long id) {
        return accessoryRepository.findById(id);
    }

    @Override
    public Page<Accessory> getAllAccessories(Pageable pageable, SearchForm searchForm) {
        return accessoryRepository.findByFilter(searchForm.getPhrase(),
                searchForm.getPriceMax(), searchForm.getPriceMin(),
                pageable);
    }

    @Override
    public List<Type> getAllAccessoryTypes() {
        return typeRepository.findAll();
    }

    @Override
    public void addToCart(CartItem accessory, Accessory item) {
        shoppingService.addToCart(accessory);
    }

    @Override
    public Page<Accessory> getAccessoryByType(Type type, Pageable pageable) {
        return accessoryRepository.findAllByType(type, pageable);
    }
}
