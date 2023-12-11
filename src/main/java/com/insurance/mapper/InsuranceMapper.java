package com.insurance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.insurance.domain.InsuranceDTO;

@Mapper                                                                // @Mapper :: 데이터베이스와 통신하기 위한 어노테이션 (SQL문을 찾아 실행)
public interface InsuranceMapper                                       
{
	public int insertInsurance(InsuranceDTO params);                   // insertInsurance :: INSERT 쿼리 호출 메서드 (게시글 생성)
	public InsuranceDTO selectInsuranceDetail (Long idx);              // selectInsuranceDetail :: SELECT 쿼리 호출 메서드 (하나의 게시글 조회)
	public int updateInsurance (InsuranceDTO params);                  // updateInsurance :: UPDATE 쿼리 호출 메서드 (게시글 수정)
	public int deleteInsurance (Long idx);                             // deleteInsurance :: DELETE 쿼리 호출 메서드 (게시글 삭제)
	public List<InsuranceDTO> selectInsuranceList();                   // selectInsuranceList :: SELECT 쿼리 호출 메서드 (게시글 목록 조회)
	public int selectInsuranceTotalCount();                            // selectInsuranceTotalCount :: SELECT 쿼리 호출 메서드 (삭제여부[delete_yn)가 'N'으로 지정된 게시글 개수 조회)

}
