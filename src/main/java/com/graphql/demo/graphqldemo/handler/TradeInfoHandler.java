package com.graphql.demo.graphqldemo.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dao.ShopDao;
import com.graphql.demo.graphqldemo.entity.Shop;
import com.graphql.demo.graphqldemo.model.Error;
import com.graphql.demo.graphqldemo.model.QueryInput;

@Component
public class TradeInfoHandler {

	@Autowired
	private ShopDao shopDao;

	public boolean doCheck(String shopId, Integer totalCount, Integer startCount, String startDate, String endDate,
			QueryInput queryInput, Error error) {

		if (!this.doTotalCountCheck(totalCount, error)) {
			return false;
		}

		if (!this.doShopCheck(shopId, error)) {
			return false;
		}
		return true;
	}

	private boolean doTotalCountCheck(Integer totalCount, Error error) {

		if (totalCount != null) {
			if (totalCount.intValue() > 100) {
				error.setCode("E001");
				error.setMessage("The maximum number of requests exceeded");
				return false;
			}
		}

		return true;
	}

	private boolean doShopCheck(String shopId, Error error) {

		List<Shop> shops = shopDao.getShopInfo(shopId);
		if (shops == null || shops.size() != 1 || shops.get(0) == null) {
			error.setCode("E002");
			error.setMessage("Abnormal store information");
			return false;
		}

		return true;
	}
}
