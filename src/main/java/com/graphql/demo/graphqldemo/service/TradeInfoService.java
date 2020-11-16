package com.graphql.demo.graphqldemo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.graphql.demo.graphqldemo.model.QueryInput;

@Service
public class TradeInfoService {

	public void doTradeInfoSearch(String shopId, Integer totalCount, Integer startCount, String startDate,
			String endDate, QueryInput queryInput) {

		this.doDefaultInputSet(totalCount, startCount, queryInput);
	}

	private void doDefaultInputSet(Integer totalCount, Integer startCount, QueryInput queryInput) {

		if (totalCount == null) {
			totalCount = 10;
		}
		if (startCount == null) {
			startCount = 1;
		}

		if (queryInput == null) {
			queryInput = new QueryInput();
			queryInput.setDateType("0");
		} else {
			if (StringUtils.isEmpty(queryInput.getDateType())) {
				queryInput.setDateType("0");
			}
		}
	}
}
