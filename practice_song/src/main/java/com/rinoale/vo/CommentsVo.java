package com.rinoale.vo;

public class CommentsVo {
	private int seq;
	private String COMMENTS_CONTENT;
	private int parentSeq;
	
	public int getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(int parent_seq) {
		this.parentSeq = parent_seq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCOMMENTS_CONTENT() {
		return COMMENTS_CONTENT;
	}
	public void setCOMMENTS_CONTENT(String COMMENT_CONTENT) {
		this.COMMENTS_CONTENT = COMMENT_CONTENT;
	}
}
