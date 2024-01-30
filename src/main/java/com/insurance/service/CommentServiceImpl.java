package com.insurance.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.domain.CommentDTO;
import com.insurance.mapper.CommentMapper;

//@Service   :: 비즈니스 로직을 수행하는 클래스임을 선언
@Service
//implements :: CommentService 인터페이스에 정의한 메서드를 구현하기 위해 'implements'를 사용
public class CommentServiceImpl implements CommentService
{
	@Autowired
	// commentMapper :: @Autowired를 사용해서 CommentMapper 빈(Beam)을 주입
	private CommentMapper commentMapper;
	
	@Override
	// registerComment :: 댓글 번호(idx)가 파라미터에 포함되어 있지 않으면 INSERT를 실행하고, 포함되어 있으면 UPDATE를 실행
	public boolean registerComment(CommentDTO params)
	{
		int queryResult = 0;
		
		if (params.getIdx() == null)
		{
			queryResult = commentMapper.insertComment(params);
		} else {
			queryResult = commentMapper.updateComment(params);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	// deleteComment :: 댓글의 상세 정보를 조회해서 정상적으로 사용 중인 댓글인 경우에만 삭제
	public boolean deleteComment(Long idx)
	{
		int queryResult = 0;
		
		CommentDTO comment = commentMapper.selectCommentDetail(idx);
		
		if (comment != null && "N".equals(comment.getDeleteYn()))
		{
			queryResult = commentMapper.deleteComment(idx);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	// getCommentList :: 특정 게시글에 포함된 댓글이 1개 이상이면 댓글 목록 반환
	public List<CommentDTO> getCommentList(CommentDTO params)
	{
		List<CommentDTO> commentList = Collections.emptyList();                       
		
		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		if (commentTotalCount > 0)
		{
			commentList = commentMapper.selectCommentList(params);
		}
		return commentList;
	}

}
