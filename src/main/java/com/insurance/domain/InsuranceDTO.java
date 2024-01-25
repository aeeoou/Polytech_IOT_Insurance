package com.insurance.domain;

import java.time.LocalDateTime;

//InsuranceDTO 가 CommonDTO 클래스를 상속받도록 변경 ( ++ extends CommonDTO)
public class InsuranceDTO extends CommonDTO
{
	private Long idx;                 // 글 번호(PK)
	
	private String insType;           // 보종
	private String insCo;             // 보험회사
	private String contractDate;      // 계약체결일
	private String contractNo;        // 계약번호
	private String contractor;        // 계약자
	private String insured;           // 피보험자
	private String prodName;          // 상품명
	private String paytExp;           // 납&만기
	private int    premium;           // 보험료
	private String memoCol;           // 메모란
	
	private String renewProdYn;       // 갱신형 상품 여부
	private String digiSigYn;         // 전자서명 여부
	private String deleteYn;          // 
	
	private LocalDateTime insertTime; // 등록일
	private LocalDateTime updateTime; // 수정일
	private LocalDateTime deleteTime; // 삭제일
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getInsType() {
		return insType;
	}
	public void setInsType(String insType) {
		this.insType = insType;
	}
	public String getInsCo() {
		return insCo;
	}
	public void setInsCo(String insCo) {
		this.insCo = insCo;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractor() {
		return contractor;
	}
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	public String getInsured() {
		return insured;
	}
	public void setInsured(String insured) {
		this.insured = insured;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getPaytExp() {
		return paytExp;
	}
	public void setPaytExp(String paytExp) {
		this.paytExp = paytExp;
	}
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	public String getRenewProdYn() {
		return renewProdYn;
	}
	public void setRenewProdYn(String renewProdYn) {
		this.renewProdYn = renewProdYn;
	}
	public String getDigiSigYn() {
		return digiSigYn;
	}
	public void setDigiSigYn(String digiSigYn) {
		this.digiSigYn = digiSigYn;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getMemoCol() {
		return memoCol;
	}
	public void setMemoCol(String memoCol) {
		this.memoCol = memoCol;
	}
	public LocalDateTime getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	@Override
	public String toString() {
		return "InsuranceDTO [idx=" + idx + ", insType=" + insType + ", insCo=" + insCo + ", contractDate="
				+ contractDate + ", contractNo=" + contractNo + ", contractor=" + contractor + ", insured=" + insured
				+ ", prodName=" + prodName + ", paytExp=" + paytExp + ", premium=" + premium + ", renewProdYn="
				+ renewProdYn + ", digiSigYn=" + digiSigYn + ", deleteYn=" + deleteYn + ", memoCol=" + memoCol
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}
}
