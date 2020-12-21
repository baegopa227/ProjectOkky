package com.okky.utils;

public class Paging {

	private int currentPage = 1;
	private int perPage = 10;
	private int totalCount;
	private int windowSize = 10;
	private int firstPageInWindow;
	private int lastPageInWindow;
	private int[] pages;
	private int totalPage;
	private int firstIndex;

	private int prevWindow;
	private int nextWindow;

	private String column;
	private int sort;

	public Paging() {

	}

	public Paging(int currentPage, int totalCount) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
	}

	public int getFirstPageInWindow() {
		return ((getCurrentWindow() - 1) * windowSize + 1);
	}

	public int getLastPageInWindow() {
		return Math.min(getCurrentWindow() * windowSize, getTotalPage());
	}

	public int[] getPages() {
		int arryLength = (getLastPageInWindow() - getFirstPageInWindow() + 1);
		arryLength = arryLength < 1 ? 1 : arryLength;

		int[] pages = new int[arryLength];

		for (int i = 0, p = getFirstPageInWindow(); p <= getLastPageInWindow(); p++, i++)
			pages[i] = p;
		return pages;
	}

	public int getCurrentWindow() {
		return (int) Math.ceil((double) currentPage / (double) windowSize);
	}

	public int getTotalPage() {
		int totalPage = (int) Math.ceil((double)totalCount / (double)perPage);
		return totalPage == 0? 1 : totalPage;
	}
	
	public int getFirstIndex() {
		return perPage * (currentPage -1);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

	public int getPerPage() {
		return perPage;
	}

	public int getPrevWindow() {
		return getFirstPageInWindow() - getWindowSize();
	}

	public int getNextWindow() {
		return getLastPageInWindow() + 1;
	}
	
	public int getTotalWindow() {
		return (int) Math.ceil((double) getTotalPage() / (double) getWindowSize());
	}
	
	
}
