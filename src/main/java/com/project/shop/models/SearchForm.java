package com.project.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("searchForm")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Data
@NoArgsConstructor
public class SearchForm {
    private String phrase = "";
    private Float priceMin;
    private Float priceMax;
    private Type type;

    public boolean isEmpty() {
        return StringUtils.isEmpty(phrase)
                && priceMax == null
                && priceMin == null
                && type == null;
    }

    public void clear() {
        this.phrase = "";
        this.priceMax = null;
        this.priceMin = null;
        this.type = null;
    }
}
