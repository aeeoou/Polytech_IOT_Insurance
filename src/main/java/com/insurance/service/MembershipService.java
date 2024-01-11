package com.insurance.service;

import com.insurance.domain.MembershipDTO;

public interface MembershipService
{
	public void registerMember(MembershipDTO params) throws Exception;
}
