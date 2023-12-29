package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.domain.CommentDTO;
import com.insurance.service.CommentService;

@RestController                                                // @RestController :: 선언된 컨트롤러의 모든 메서드는 화면이 아닌, 리턴 타입에 해당하는 데이터 자체를 리턴
public class CommentController
{
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/comments/{insuranceIdx}")
	public List<CommentDTO> getCommentList(@PathVariable("insuranceIdx") Long insuranceIdx, @ModelAttribute("params") CommentDTO params)   // @PathVariable :: @RequestParam과 유사한 기능을 하며, REST 방식에서 리소를 표현할 때 사용
	{                                                                                                                                      //               :: @PathVariable 은 URI에 파라미터로 전달받을 변수 지정
		List<CommentDTO> commentList = commentService.getCommentList(params);                                                              //               :: "/comments/{insurance}" URI의 {insurance}는 게시글 번호를 의미하며, @PathVarialbe의 insuranceIdx와 매핑(바인딩)
		return commentList;                                                                                 // @ModelAttribute :: 파라미터로 전달받은 객체를 자동으로 화면(뷰)로 전달
	}                                                                                                       //                 :: 특정 게시글에 등록된 댓글을 조회
}                                                                                                           //                 :: 추후 댓글 목록의 페이징 처리에 활용

                                                                                                            // 로직 :: getCommentList 메서드를 호출한 결과를 commentList에 저장