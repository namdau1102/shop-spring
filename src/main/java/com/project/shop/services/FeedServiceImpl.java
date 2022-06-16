package com.project.shop.services;

import com.project.shop.models.CartItem;
import com.project.shop.models.Feed;
import com.project.shop.models.SearchForm;
import com.project.shop.models.Type;
import com.project.shop.repositories.FeedRepository;
import com.project.shop.repositories.TypeRepository;
import com.project.shop.services.declarations.FeedService;
import com.project.shop.services.declarations.FileService;
import com.project.shop.services.declarations.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("feedService")
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;

    private final TypeRepository typeRepository;

    private final ShoppingService shoppingService;

    private final FileService fileService;

    @Autowired
    public FeedServiceImpl(FeedRepository feedRepository, TypeRepository typeRepository, ShoppingService shoppingService, FileService fileService) {
        this.feedRepository = feedRepository;
        this.typeRepository = typeRepository;
        this.shoppingService = shoppingService;
        this.fileService = fileService;
    }

    @Override
    public void save(Feed feed, MultipartFile[] multipartFile, HttpServletRequest httpRequest) throws IOException {
        for (MultipartFile file : multipartFile)
            fileService.save(file, httpRequest);

        List<String> images = Arrays.stream(multipartFile)
                .filter(e -> !e.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .map(e -> "/uploads/" + e)
                .collect(Collectors.toList());

        feed.setImages(images);
        feedRepository.saveAndFlush(feed);
    }

    @Override
    public void update(Feed feed, MultipartFile[] files, HttpServletRequest httpRequest) throws IOException {
        save(feed, files, httpRequest);
    }

    @Override
    public void delete(Feed feed) {
        feed.setDeleted(Boolean.TRUE);
        feedRepository.save(feed);
    }


    @Override
    public Optional<Feed> getFeed(long id) {
        return feedRepository.findById(id);
    }

    @Override
    public Page<Feed> getAllFeeds(Pageable pageable, SearchForm searchForm) {
        return feedRepository.findByFilter(searchForm.getPhrase(),
                searchForm.getPriceMax(), searchForm.getPriceMin(),
                pageable);
    }

    @Override
    public List<Type> getAllFeedTypes() {
        return typeRepository.findAll();
    }

    @Override
    public void addToCart(CartItem feed, Feed item) {
        shoppingService.addToCart(feed);
    }

    @Override
    public Page<Feed> getFeedByType(Type type, Pageable pageable) {
        return feedRepository.findAllByType(type, pageable);
    }
}
