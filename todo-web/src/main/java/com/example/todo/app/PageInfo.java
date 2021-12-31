package com.example.todo.app;

import org.springframework.data.domain.Page;
import org.thymeleaf.util.NumberUtils;

public class PageInfo {
	
    // (1)
    public <T> Integer[] sequence(Page<T> page, int pageLinkMaxDispNum) {

        // (2)
        int begin = Math.max(1, page.getNumber() + 1 - pageLinkMaxDispNum / 2);
        int end = begin + (pageLinkMaxDispNum - 1);
        if (end > page.getTotalPages() - 1) {
            end = page.getTotalPages();
            begin = Math.max(1, end - (pageLinkMaxDispNum - 1));
        }

        // (3)
        return NumberUtils.sequence(begin, end);
    }
    
}