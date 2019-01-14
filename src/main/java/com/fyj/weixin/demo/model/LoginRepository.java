package com.fyj.weixin.demo.model;

import org.springframework.data.repository.PagingAndSortingRepository;


// springdata已经帮我们实现了基本的增删改查
// CRUD --> Create（增）, Read（查）, Update（改）, Delete（删）
public interface LoginRepository extends PagingAndSortingRepository<LoginEntity, Long> {

}