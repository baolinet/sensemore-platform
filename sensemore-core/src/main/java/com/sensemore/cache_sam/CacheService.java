package com.sensemore.cache_sam;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @Cacheable(value = "books", key = "#p0")
    public Book findBookByIsbn(String isbn) {

        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }

        // 模拟数据库查询或其他耗时操作
        System.out.println("第一次数据库查询");
        Book book = new Book(isbn, "Example Book");
        System.out.println("Found book: " + book);
        return book;
    }
    
}
