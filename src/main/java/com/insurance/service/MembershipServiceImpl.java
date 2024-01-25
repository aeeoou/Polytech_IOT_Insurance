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
	
	@Override
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
	public MembershipDTO getMembershipDetail(long idx)
	{
		return membershipMapper.selectMembershipDetail(idx);
	}
	
	@Override
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
	
	// 회원아이디로 정보를 가져오는 함수를 실제 구현하는 곳
	@Override
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
	
	// 로그아웃
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public void logout()
	{
		if (httpSession != null)
		{
			httpSession.invalidate();
		}
	}
}