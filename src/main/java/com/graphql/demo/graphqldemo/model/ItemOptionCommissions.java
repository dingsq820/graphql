package com.graphql.demo.graphqldemo.model;

import java.util.List;

public class ItemOptionCommissions {

	private String itemOptionCommissionTitle;

	private List<ItemOptionCommissionVal> itemOptionCommissionVal;

	private Integer itemOptionCommissionSeq;

	public String getItemOptionCommissionTitle() {
		return itemOptionCommissionTitle;
	}

	public void setItemOptionCommissionTitle(String itemOptionCommissionTitle) {
		this.itemOptionCommissionTitle = itemOptionCommissionTitle;
	}

	public List<ItemOptionCommissionVal> getItemOptionCommissionVal() {
		return itemOptionCommissionVal;
	}

	public void setItemOptionCommissionVal(List<ItemOptionCommissionVal> itemOptionCommissionVal) {
		this.itemOptionCommissionVal = itemOptionCommissionVal;
	}

	public Integer getItemOptionCommissionSeq() {
		return itemOptionCommissionSeq;
	}

	public void setItemOptionCommissionSeq(Integer itemOptionCommissionSeq) {
		this.itemOptionCommissionSeq = itemOptionCommissionSeq;
	}

}
