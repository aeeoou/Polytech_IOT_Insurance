package com.insurance.service;

import org.springframework.stereotype.Service;

import com.insurance.domain.MembershipDTO;
import com.insurance.mapper.MembershipMapper;

import jakarta.inject.Inject;

@Service
public class MembershipServiceImpl implements MembershipService
{
	@Inject
	MembershipMapper dao;
	
	@Override
	public void registerMember(MembershipDTO params) throws Exception
	{
		dao.register(params);
	}

}
