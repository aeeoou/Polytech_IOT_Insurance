package com.insurance;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.insurance.domain.InsuranceDTO;
import com.insurance.mapper.InsuranceMapper;

@SpringBootTest
class MapperTests
{
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	// testOfInsert 게시글 작성 단위 테스트
	@Test
	public void testOfInsert()
	{
		InsuranceDTO params = new InsuranceDTO();
		
		params.setInsType     ("1번 보험종목");
		params.setInsCo       ("1번 보험회사");
		params.setContractDate("1번 계약체결일");
		params.setContractNo  ("1번 계약번호");
		params.setContractor  ("1번 계약자");
		params.setInsured     ("1번 피보험자"); 
		params.setProdName    ("1번 상품명");
		params.setPaytExp     ("1번 납&만기");
		params.setPremium     (1);
		params.setMemoCol     ("1번 메모란");
		
		int result = insuranceMapper.insertInsurance(params);

		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	// testOfUpdate 게시글 조회 기능 메서드 테스트
	public void testOfSelectDetail()
	{
		InsuranceDTO insurance = insuranceMapper.selectInsuranceDetail((long) 1);
		
		try
		{
			String insuranceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(insurance);
			
			System.out.println("========================================");
			System.out.println(insuranceJson);
			System.out.println("========================================");
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// testOfUpdate 게시글 수정 기능 메서드 작성
	public void testOfUpdate()
	{
		InsuranceDTO params = new InsuranceDTO();
		
		params.setInsType      ("1번 보험 종목을 수정");
		params.setInsCo        ("1번 보험회사 이름을 수정");
		params.setContractDate ("1번 계약체결일을 수정");
		params.setContractNo   ("1번 계약번호를 수정");
		params.setContractor   ("1번 계약자 이름을 수정");
		params.setInsured      ("1번 피보험자 이름을 수정");
		params.setProdName     ("1번 상품명을 수정");
		params.setPaytExp      ("1번 납&만기를 수정");
		params.setPremium      (1);
		params.setMemoCol      ("1번 메모란 수정");
		params.setIdx((long) 1);
		
		int result = insuranceMapper.updateInsurance(params);
		
		if (result == 1)
		{
			InsuranceDTO insurance = insuranceMapper.selectInsuranceDetail((long) 1);
			
			try
			{
				String insuranceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(insurance);
				
				System.out.println("========================================");
				System.out.println(insuranceJson);
				System.out.println("========================================");
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	// testOfUpdate 게시글 삭제 기능 메서드 작성
	public void testOfDelete()
	{
		int result = insuranceMapper.deleteInsurance((long) 1);
		
		if (result == 1)
		{
			InsuranceDTO insurance = insuranceMapper.selectInsuranceDetail((long) 1);
			try
			{
				String insuranceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(insurance);
				
				System.out.println("========================================");
				System.out.println(insuranceJson);
				System.out.println("========================================");
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void testOfMultiple()
	{
		InsuranceDTO params = new InsuranceDTO();
		
		for(int i = 0; i < 50; i++)
		{
			params.setInsType      (i + "번 게시글 보험종목");
			params.setInsCo        (i + "번 게시글 보험회사");
			params.setContractDate (i + "번 게시글 계약체결일");
			params.setContractNo   (i + "번 게시글 계약번호");
			params.setContractor   (i + "번 게시글 계약자");
			params.setInsured      (i + "번 게시글 피보험자");
			params.setProdName     (i + "번 게시글 상품명");
			params.setPaytExp      (i + "번 납&만기");
			params.setMemoCol      (i + "번 메모란");
			
			int result = insuranceMapper.insertInsurance(params);
			
			System.out.println("결과는 " + result + "입니다.");
		}
	}
	
	@Test
	// testOfUpdate 게시글 목록 조회 기능 메서드 작성
	public void testSelectList()
	{
		int insuranceTotalCount = insuranceMapper.selectInsuranceTotalCount(null);
		
		if (insuranceTotalCount > 0)
		{
			List<InsuranceDTO> insuranceList = insuranceMapper.selectInsuranceList(null);
			
			if (CollectionUtils.isEmpty(insuranceList) == false)
			{
				for (InsuranceDTO insurance : insuranceList)
				{
					System.out.println("========================================");
					System.out.println(insurance.getInsType());
					System.out.println(insurance.getInsCo());
					System.out.println(insurance.getContractDate());
					System.out.println(insurance.getContractNo());
					System.out.println(insurance.getContractor());
					System.out.println(insurance.getInsured());
					System.out.println(insurance.getProdName());
					System.out.println(insurance.getPaytExp());
					System.out.println(insurance.getPremium());
					System.out.println(insurance.getMemoCol());
					System.out.println("========================================");
				}
			}
		}
	}
}
