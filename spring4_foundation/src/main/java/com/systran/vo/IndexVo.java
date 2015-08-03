package com.systran.vo;

public class IndexVo {
	private int fromIndex;
	private int howMany;
	private int seq;
	private String sortBy;
	private String ASC_or_DESC;
	public int getFromIndex() {
		return fromIndex;
	}
	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}
	public int getHowMany() {
		return howMany;
	}
	public void setHowMany(int howMany) {
		this.howMany = howMany;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getASC_or_DESC() {
		return ASC_or_DESC;
	}
	public void setASC_or_DESC(String aSC_or_DESC) {
		ASC_or_DESC = aSC_or_DESC;
	}
	

}
