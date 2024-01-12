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
		
		params.setUserId("사용자 아이디");
		params.setUserPw("사용자 패드워드");
		params.setUserEmail("사용자 이메일");
		params.setUserName("사용자 이름");
		params.setUserBirth("사용자 생년월일");
		params.setUserGender("사용자 성별");
		params.setUserPhone("사용자 전화번호");
		params.setQuitYn("사용자 탈퇴여부");
		
		int result = membershipMapper.register(params);
		
		System.out.println("결과는 " + result + "입니다.");
	}
	
}
