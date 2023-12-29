package com.insurance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.insurance.domain.CommentDTO;
import com.insurance.service.CommentService;

@SpringBootTest
class CommentTests
{
	@Autowired
	private CommentService commentService;
	
	@Test
	// 댓글을 등록하는 메서드
	public void registerComments()
	{
		int number = 50;                      
		
		for (int i = 1; i <= number; i++)
		{
			CommentDTO params = new CommentDTO();
			// 댓글을 추가할 게시글 번호
			params.setInsuranceIdx((long) 134);
			params.setContent(i + "번 댓글을 추가합니다!");
			params.setWriter(i + "번 설계사");
			commentService.registerComment(params);
		}
		System.out.println("댓글 " + number + "개가 등록되었습니다.");
	}
	
	@Test
	public void deleteComment()
	{
		commentService.deleteComment((long) 164);           // 삭제할 댓글 번호
		
		getCommentList();
	}
	
	@Test
	public void getCommentList()                         // 등록한 댓글을 조회하는 메서드
	{
		CommentDTO params = new CommentDTO();
		params.setInsuranceIdx((long) 10);               // 댓글을 추가할 게시글 번호
		
		for(CommentDTO comment : commentService.getCommentList(params))
		{
			System.out.println("=======================================");
			System.out.println(comment.getInsuranceIdx());
			System.out.println(comment.getContent());
			System.out.println(comment.getWriter());
			System.out.println("=======================================");
		}
	}
}
