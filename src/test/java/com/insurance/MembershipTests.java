package com.insurance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.insurance.domain.MembershipDTO;
import com.insurance.mapper.MembershipMapper;

@SpringBootTest
class MembershipTests
{
	@Autowired
	private MembershipMapper membershipMapper;
	
	@Test
	public void testOfInsert()
	{
		MembershipDTO params = new MembershipDTO();
		
		params.setUserId        ("aeeoou");
		params.setUserPw        ("486521");
		params.setUserEmail     ("aeeoou@gmail.com");
		params.setUserName      ("서형종");
		params.setUserBirth     ("910409");
		params.setUserGender    ("M");
		params.setUserPhone     ("010-2442-0030");
		// 기본값 'N'으로 설정
		params.setQuitYn        ("N");
		params.setMasterYn      ("N");
		
		int result = membershipMapper.insertMembership(params);
		
		System.out.println("결과는 " + result + "입니다.");
	}
	
}
