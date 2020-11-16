package com.graphql.demo.graphqldemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.entity.Shop;

@Component
@Mapper
public interface ShopDao {

	public List<Shop> getShopInfo(String shopId);
}
