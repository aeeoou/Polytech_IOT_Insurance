package com.insurance.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuranceDTO
{
	private Long idx;                 // 글 번호(PK)
	
	private String ins_type;          // 보종
	private String ins_co;            // 보험회사
	private String contract_date;     // 계약체결일
	private String contract_no;       // 계약번호	
	private String contractor;        // 계약자
	private String insured;           // 피보험자
	private String prod_name;         // 상품명
	private String payt_exp;          // 납&만기
	
	private int    premium;           // 보험료
	
	private String renew_prod_Yn;     // 갱신형 상품 여부
	private String digi_sig_Yn;       // 전자서명 여부
	private String delete_Yn;         // 
	 
	private String memo_col;          // 메모란
	
	private LocalDateTime insertTime; // 등록일
	private LocalDateTime updateTime; // 수정일
	private LocalDateTime deleteTime; // 삭제일
	
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getIns_type() {
		return ins_type;
	}
	public void setIns_type(String ins_type) {
		this.ins_type = ins_type;
	}
	public String getIns_co() {
		return ins_co;
	}
	public void setIns_co(String ins_co) {
		this.ins_co = ins_co;
	}
	public String getContract_date() {
		return contract_date;
	}
	public void setContract_date(String contract_date) {
		this.contract_date = contract_date;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
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
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getPayt_exp() {
		return payt_exp;
	}
	public void setPayt_exp(String payt_exp) {
		this.payt_exp = payt_exp;
	}
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	public String getRenew_prod_Yn() {
		return renew_prod_Yn;
	}
	public void setRenew_prod_Yn(String renew_prod_Yn) {
		this.renew_prod_Yn = renew_prod_Yn;
	}
	public String getDigi_sig_Yn() {
		return digi_sig_Yn;
	}
	public void setDigi_sig_Yn(String digi_sig_Yn) {
		this.digi_sig_Yn = digi_sig_Yn;
	}
	public String getDelete_Yn() {
		return delete_Yn;
	}
	public void setDelete_Yn(String delete_Yn) {
		this.delete_Yn = delete_Yn;
	}
	public String getMemo_col() {
		return memo_col;
	}
	public void setMemo_col(String memo_col) {
		this.memo_col = memo_col;
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
		return "InsuranceDTO [idx=" + idx + ", ins_type=" + ins_type + ", ins_co=" + ins_co + ", contract_date="
				+ contract_date + ", contract_no=" + contract_no + ", contractor=" + contractor + ", insured=" + insured
				+ ", prod_name=" + prod_name + ", payt_exp=" + payt_exp + ", premium=" + premium + ", renew_prod_Yn="
				+ renew_prod_Yn + ", digi_sig_Yn=" + digi_sig_Yn + ", delete_Yn=" + delete_Yn + ", memo_col=" + memo_col
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}   
}
