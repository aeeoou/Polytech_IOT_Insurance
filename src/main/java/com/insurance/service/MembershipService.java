package com.insurance.service;

import java.util.List;

import com.insurance.domain.MembershipDTO;

public interface MembershipService
{
	 public boolean registerMembership(MembershipDTO params);
	 public MembershipDTO getMembershipDetail(long idx);
	 public boolean deleteMembership(long idx);
	 public List<MembershipDTO> getMembershipList();
}
