package com.insurance.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.domain.MembershipDTO;
import com.insurance.mapper.MembershipMapper;

@Service
public class MembershipServiceImpl implements MembershipService
{
	@Autowired
	private MembershipMapper membershipMapper;
	
	@Override
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
}
