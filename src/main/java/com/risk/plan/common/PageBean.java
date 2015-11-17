package com.risk.plan.common;

import java.util.List;

public class PageBean {
	private boolean hasFirst;
	private long first;
	private boolean hasEnd;
	private long end;
	private boolean hasPreview;
	private long preview;
	private boolean hasNext;
	private long next;
	private long current;
	public static long pagecount = 2;
	public static final String count="select count(*)";
	private long total;
	private long totalpage;
	private Double money;
	private String url;
	@SuppressWarnings("unchecked")
	private List list;
	
	public PageBean() {

	}

	@SuppressWarnings("unchecked")
	/***
	 * current 当前�?
	 * pagecount 每页多少条数�?
	 * total 总条�?
	 * list
	 */
	public PageBean(long current, long pagecount, long total, List list) {
		this.current = current;
		this.pagecount = pagecount;
		this.total = total;
		this.list = list;
		totalpage = total / pagecount;
		if (total % pagecount != 0) {
			totalpage++;
		}
		if (totalpage > 0 && this.current > 1) {
			hasFirst = true;
			first = 1;
		}
		if (this.current > 1 && this.current - 1 <= totalpage) {
			hasPreview = true;
			preview = this.current - 1;
		}
		if (this.current >= 1 && this.current < totalpage) {
			hasNext = true;
			next = this.current + 1;
		}
		if (totalpage > 0 && current != totalpage) {
			hasEnd = true;
			end = totalpage;
		}
	}

	public boolean isHasFirst() {
		return hasFirst;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	public long getFirst() {
		return first;
	}

	public void setFirst(long first) {
		this.first = first;
	}

	public boolean isHasEnd() {
		return hasEnd;
	}

	public void setHasEnd(boolean hasEnd) {
		this.hasEnd = hasEnd;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public boolean isHasPreview() {
		return hasPreview;
	}

	public void setHasPreview(boolean hasPreview) {
		this.hasPreview = hasPreview;
	}

	public long getPreview() {
		return preview;
	}

	public void setPreview(long preview) {
		this.preview = preview;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public long getNext() {
		return next;
	}

	public void setNext(long next) {
		this.next = next;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getPagecount() {
		return pagecount;
	}

	public void setPagecount(long pagecount) {
		this.pagecount = pagecount;
	}

	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalpage() {
		return totalpage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
