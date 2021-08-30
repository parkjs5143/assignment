package com.koreait.day3.service;

import com.koreait.day3.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {
    public List<AdminMenu> getAdminMenu(){
        return Arrays.asList(
                AdminMenu.builder().title("HOME").url("/pages").code("main").img("home").build(),
                AdminMenu.builder().title("고객관리").url("/pages/user").img("account_circle").code("user").build(),
                AdminMenu.builder().title("관리자관리").url("/pages/adminuser").img("manage_accounts").code("adinmuser").build(),
                AdminMenu.builder().title("카테고리관리").url("/pages/category").img("category").code("category").build(),
                AdminMenu.builder().title("아이템관리").url("/pages/item").img("production_quantity_limits").code("item").build(),
                AdminMenu.builder().title("구매정보관리").url("/pages/ordergroup").img("shopping_cart").code("ordergroup").build(),
                AdminMenu.builder().title("업체관리").url("/pages/partner").img("group_work").code("partner").build()
        );
    }
}
