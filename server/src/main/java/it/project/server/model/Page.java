package it.project.server.model;

import java.util.List;

public class Page<T> {
	
	private Integer currentPage;
	private Integer perPage;
	private Integer nRows;
	private List<T> list;

	public Integer getnRows() {
		return nRows;
	}
	public void setnRows(Integer nRows) {
		this.nRows = nRows;
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	public Page() {
		
	}
	
	public Page(Integer currentPage, Integer nRows, Integer perPage, List<T> list) {
		this.currentPage = currentPage;
		this.perPage = perPage;
		this.nRows = nRows;
		this.list = list;
	}
	
}
