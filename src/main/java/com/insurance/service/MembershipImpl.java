package com.insurance.service;

import org.springframework.stereotype.Service;

import com.insurance.dao.MembershipDAO;
import com.insurance.vo.MembershipVO;

import jakarta.inject.Inject;

public class MembershipImpl implements MembershipService
{
	@Inject
	MembershipDAO dao;
	
	@Override
	public void register(MembershipVO vo) throws Exception
	{
		dao.register(vo);
	}

}
