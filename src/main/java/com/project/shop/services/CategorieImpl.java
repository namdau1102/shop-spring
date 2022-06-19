package com.project.shop.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.project.shop.models.Type;
import com.project.shop.repositories.TypeRepository;
import com.project.shop.services.declarations.CategorieService;

@Service("CategorieService")
public class CategorieImpl implements CategorieService {

	private final TypeRepository typeRepository;

	@Autowired
	public CategorieImpl(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<Type> findAll() {
		

		return typeRepository.findAll();

	}

	@Override
	public Optional<Type> getTypeById(Long id) {
		// TODO Auto-generated method stub
		Optional<Type> type = typeRepository.findById(id);
		return type;
	}

	@Override
	public void update(Type type, Long id) {
		// TODO Auto-generated method stub
		typeRepository.save(type);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		typeRepository.deleteById(id);

	}

	@Override
	public void save(Type type) {
		typeRepository.save(type);
	}

}
