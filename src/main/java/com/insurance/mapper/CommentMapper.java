package com.insurance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.insurance.domain.CommentDTO;

//@Mapper :: 데이터베이스와 통신하는 인터페이스임을 선언
@Mapper
public interface CommentMapper             
{
	// 구현체는 mapper.xml
	
	// insertComment           :: 댓글을 삽입하는 INSERT 쿼리 호출
	public int insertComment(CommentDTO params);
	// selectCommentDetail     :: 파라미터로 전달받은 댓글 번호(idx)에 해당하는 댓글의 상세 내용을 조회
	public CommentDTO selectCommentDetail(Long idx);
	// updateComment           :: 댓글을 수정하는 UPDATE 쿼리 호출
	public int updateComment(CommentDTO params);
	// deleteComment           :: 댓글을 삭제하는 메서드 (DELETE 쿼리를 호출하지 않고, UPDATE 쿼리를 호출해서 delete_yn 컬럼의 상태를 'Y'로 지정
	public int deleteComment(Long idx);
	// selectCommentList       :: 특정 게시글에 포함된 댓글 목록을 조회하는 SELECT 쿼리 호출
	public List<CommentDTO> selectCommentList(CommentDTO params);
	// selectCommentTotalCount :: 특정 게시글에 포함된 댓글 개수를 조회하는 SELECT 쿼리 호출
	public int selectCommentTotalCount(CommentDTO params);
}
