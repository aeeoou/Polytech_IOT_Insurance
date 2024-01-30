package com.insurance.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.domain.MembershipDTO;
import com.insurance.mapper.MembershipMapper;

import jakarta.servlet.http.HttpSession;


@Service
public class MembershipServiceImpl implements MembershipService
{
	@Autowired
	private MembershipMapper membershipMapper;
	
	// HttpSession 추가
	@Autowired
	private HttpSession httpSession;
	
	@Override
	// 회원 가입을 처리하는 기능 (회원 가입 로직)
	//      함수타입        함수명         매개변수의 타입   매개변수
	public boolean registerMembership(MembershipDTO params)
	{
		int queryResult = 0;
		
		if (params.getUserIdx() == null)
		{
			queryResult = membershipMapper.insertMembership(params);

		} else {
			queryResult = membershipMapper.updateMembership(params);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	// 회원의 상세 정보를 조회하는 기능 (회원 상세 정보 조회 로직)
	public MembershipDTO getMembershipDetail(long idx)
	{
		return membershipMapper.selectMembershipDetail(idx);
	}
	
	@Override
	// 회원을 삭제하는 기능 (회원 삭제 로직)
	public boolean deleteMembership(long idx)
	{
		int queryResult = 0;
		
		MembershipDTO membership = membershipMapper.selectMembershipDetail(idx);
		
		if (membership != null && "N".equals(membership.getQuitYn()))
		{
			queryResult = membershipMapper.deleteMembership(idx);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	// 회원 리스트 조회 로직
	public List<MembershipDTO> getMembershipList()
	{
		List<MembershipDTO> membershipList = Collections.emptyList();
		
		int membershipTotalCount = membershipMapper.selectMembershipTotalCount();
		
		if (membershipTotalCount > 0)
		{
			membershipList = membershipMapper.selectMembershipList();
		}
		
		return membershipList;
	}
	
	@Override
	// 회원아이디로 정보를 가져오는 함수를 실제 구현하는 곳 (회원 아이디로 정보 조회 로직)
	public MembershipDTO getMembershipByUserId(String userId)
	{
		return membershipMapper.selectMembershipByUserId(userId);
	}
	
	@Override
	public boolean login(String userId, String userPw)
	{
		//int a = 5;
		MembershipDTO memberInfo = membershipMapper.selectMembershipByUserId(userId);
		
		// 사용자가 존재하는지 확인
		if (memberInfo != null)
		{
			// 제공된 비밀번호가 저장된 비밀번호와 일치하는지 확인
			if (userPw.equals(memberInfo.getUserPw()))
			{	
				// 로그인 성공
				return true;
			}
			// 비밀번호가 일치하지 않음
			else
			{
				System.out.println("비밀번호가 일치하지 않습니다. 사용자: " + userId);
			}
		}
		// 사용자가 존재하지 않음
		else
		{
			System.out.println("사용자를 찾을 수 없습니다. 사용자: " + userId);
		}
		// 로그인 실패
		return false;
	}

	@Override
	public void logout()
	{
		if (httpSession != null)
		{
			httpSession.invalidate();
		}
	}
}