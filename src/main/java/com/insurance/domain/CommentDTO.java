package com.insurance.domain;

public class CommentDTO
{
	private Long idx;
	private Long insuranceIdx;
	
	private String content;
	private String writer;
	private String deleteYn;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public Long getInsuranceIdx() {
		return insuranceIdx;
	}
	public void setInsuranceIdx(Long insuranceIdx) {
		this.insuranceIdx = insuranceIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [idx=" + idx + ", insuranceIdx=" + insuranceIdx + ", content=" + content + ", writer="
				+ writer + ", deleteYn=" + deleteYn + "]";
	}
}
