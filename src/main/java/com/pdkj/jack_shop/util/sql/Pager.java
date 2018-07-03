package com.pdkj.jack_shop.util.sql;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台easyui要求格式
 * @author hp
 *
 */
public class Pager {

	private int row = 30;//每页显示多少条
	
	private int page = 1;//当前页
	
	private long total = 0;
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
}
