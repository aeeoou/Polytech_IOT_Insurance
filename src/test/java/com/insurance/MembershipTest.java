package com.insurance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.insurance.dao.MembershipDAO;
import com.insurance.service.MembershipService;
import com.insurance.vo.MembershipVO;

// 회원가입 기능 테스트
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class MembershipTest
{
	@Autowired
	private MembershipService membership;
	
	@MockBean
	private MembershipDAO membershipDAO;
	
	@Test
	public void testRegister() throws Exception
	{
		// 테스트에 필요한 가입 정보를 생성
		MembershipVO testMembershipVO = new MembershipVO();
		testMembershipVO.setUserId("testUser");
		testMembershipVO.setUserPw("testPassword");
		
		// DAO 메소드가 호출될 때 예상되는 동작을 정의
		when(membershipDAO.register(any(MembershipVO.class))).thenReturn(1);
		
		// 실제 서비스 메소드 호출
		membershipService.register(testMembershipVO);
		
		// 특정 동작이 수행되었는지 검증
		verify(membershipDAO, times(1)).register(any(MembershipVO.class));
	}
}
