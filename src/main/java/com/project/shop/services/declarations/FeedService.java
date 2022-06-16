package com.project.shop.services.declarations;

import com.project.shop.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FeedService {
    void save(Feed feed, MultipartFile[] multipartFile, HttpServletRequest httpRequest) throws IOException;

    void update(Feed feed, MultipartFile[] files, HttpServletRequest httpRequest) throws IOException;

    void delete(Feed feed);

    Optional<Feed> getFeed(long id);

    Page<Feed> getAllFeeds(Pageable pageable, SearchForm searchForm);

    List<Type> getAllFeedTypes();

    void addToCart(CartItem feed, Feed item);

    Page<Feed> getFeedByType(Type type, Pageable pageable);
}
