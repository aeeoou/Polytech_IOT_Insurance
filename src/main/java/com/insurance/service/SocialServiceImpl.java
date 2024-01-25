package com.insurance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SocialServiceImpl implements SocialService
{
	@Value("${vyxyV2RuhkkRjdwhWdMklEWWU83yFDXi}")
	private String KAKAO_CLIENT_ID;
	
	@Value("${vyxyV2RuhkkRjdwhWdMklEWWU83yFDXi}")
	private String KAKAO_CLIENT_SECRET;
	
	@Value("${http://localhost:8080/kakao/insurance}")
	private String KAKAO_REDIRECT_URL;
	
	private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
	private final static String KAKAO_API_URI = "https://kapi.kakao.com";
	
	@Override
	public String getKakaoLogin()
	{
		return KAKAO_AUTH_URI + "/oauth/authorize"
				+ "?client_id=" + KAKAO_CLIENT_ID
				+ "&redirect_uri=" + KAKAO_REDIRECT_URL
				+ "&response_type=code";
	}
}
