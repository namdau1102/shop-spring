package com.project.shop.services;

import com.project.shop.models.CartItem;
import com.project.shop.models.Pet;
import com.project.shop.models.SearchForm;
import com.project.shop.models.Type;
import com.project.shop.repositories.PetRepository;
import com.project.shop.repositories.TypeRepository;
import com.project.shop.services.declarations.FileService;
import com.project.shop.services.declarations.PetService;
import com.project.shop.services.declarations.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service("petService")
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    private final TypeRepository typeRepository;

    private final ShoppingService shoppingService;

    private final FileService fileService;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, TypeRepository typeRepository, ShoppingService shoppingService, FileService fileService) {


        this.petRepository = petRepository;
        this.typeRepository = typeRepository;
        this.shoppingService = shoppingService;
        this.fileService = fileService;
    }


    @Override
    public void save(Pet pet, MultipartFile[] multipartFile,
                     HttpServletRequest httpRequest) throws IOException {
        for (MultipartFile file : multipartFile)
            fileService.save(file, httpRequest);

        List<String> images = Arrays.stream(multipartFile)
                .filter(e -> !e.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .map(e -> "/uploads/" + e)
                .collect(Collectors.toList());

        pet.setImages(images);
        petRepository.save(pet);
    }

    @Override
    public void update(Pet pet, MultipartFile[] files,
                       HttpServletRequest httpRequest) throws IOException {
        save(pet, files, httpRequest);
    }

    @Override
    public void delete(Pet pet) {
        pet.setDeleted(Boolean.TRUE);
        petRepository.save(pet);
    }


    @Override
    public Optional<Pet> getAnimal(long id) {
        return petRepository.findById(id);
    }


    @Override
    public Page<Pet> getAllPets(Pageable pageable, SearchForm searchForm) {
        return petRepository.findByFilter(searchForm.getPhrase(),
                pageable);
    }

    @Override
    public List<Type> getAllPetTypes() {
        return typeRepository.findAll();
    }

    @Override
    public void addToCart(CartItem cartItem) {
        shoppingService.addToCart(cartItem);
    }


    @Override
    public Page<Pet> getAnimalByType(Type type, Pageable pageable) {
        return petRepository.findAllByType(type, pageable);
    }


	@Override
	public List<Pet> getAllPetValue() {
		// TODO Auto-generated method stub
		return petRepository.findAllList();
	}


}
