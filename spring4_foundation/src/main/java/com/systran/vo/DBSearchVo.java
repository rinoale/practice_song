package com.systran.vo;

public class DBSearchVo {
	private IndexVo indexVo;
	private String chosen_column;
	private String text_to_search;
	public IndexVo getIndexVo() {
		return indexVo;
	}
	public void setIndexVo(IndexVo indexVo) {
		this.indexVo = indexVo;
	}
	public String getChosen_column() {
		return chosen_column;
	}
	public void setChosen_column(String chosen_column) {
		this.chosen_column = chosen_column;
	}
	public String getText_to_search() {
		return text_to_search;
	}
	public void setText_to_search(String text_to_search) {
		this.text_to_search = text_to_search;
	}

}
