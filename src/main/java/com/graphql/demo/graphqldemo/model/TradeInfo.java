package com.graphql.demo.graphqldemo.model;

import java.util.List;

public class TradeInfo {

	private Result result;

	private Integer resultCount;

	private List<OrderInfo> orderInfo;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

	public List<OrderInfo> getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(List<OrderInfo> orderInfo) {
		this.orderInfo = orderInfo;
	}

}
