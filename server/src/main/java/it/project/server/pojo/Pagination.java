package it.project.server.pojo;


import org.apache.ibatis.session.RowBounds;

public class Pagination {
	private int nPages;
	private Integer selectedPage;
	private boolean next;
	private boolean prev;
	private RowBounds rowBounds;
	private int limit;
	private int offset = 0;

	public Pagination(int limit, int nRows, Integer selectedPage) {
		this.limit = limit;
		this.selectedPage = selectedPage;

		if (this.selectedPage != null) {
			this.selectedPage--;
			this.offset = limit * this.selectedPage;
			this.selectedPage++;
		} else
			this.selectedPage = 1;

		this.nPages = (int) Math.ceil((float) nRows / (float) this.limit);
		this.next = (this.selectedPage == this.nPages) ? false : true;
		this.prev = (this.selectedPage == 1) ? false : true;
		this.rowBounds = new RowBounds(this.offset, this.limit);
	}

	public int getnPages() {
		return nPages;
	}

	public Integer getSelectedPage() {
		return selectedPage;
	}

	public boolean isNext() {
		return next;
	}

	public boolean isPrev() {
		return prev;
	}

	public RowBounds getRowBounds() {
		return rowBounds;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

}
