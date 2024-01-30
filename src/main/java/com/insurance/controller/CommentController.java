package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.domain.CommentDTO;
import com.insurance.service.CommentService;
import com.google.gson.JsonObject;

//@RestController :: 선언된 컨트롤러의 모든 메서드는 화면이 아닌, 리턴 타입에 해당하는 데이터 자체를 리턴
@RestController
public class CommentController
{
	@Autowired
	private CommentService commentService;
	
	// 게시글의 경우, 하나의 URI로 생성(INSERT)과 수정(UPDATE) 처리가 가능하지만, 'REST API'는 설계 규칙을 지켜야 하기 때문에 URI를 구분함
	// value "/comments" :: 새로운 댓글 등록, "/comments/{idx} :: 댓글 테이블의 PK인 댓글 번호(idx)에 해당하는 댓글 수정
	// RequestMethod.POST  :: HTTP 요청 메서드 중 POST를 의미하며, @PostMapping과 유사
	// RequestMethod.PATCH :: HTTP 요청 메서드 중 PATCH를 의미하며, @PatchMapping과 유사 
	
	// RequestBody :: REST 방식의 처리에 사용. 파라미터 앞에 @RequestBody가 지정되면, 파라미터로 전달받은 JSON 문자열을 객체로 변환
	// 클라이언트(사용자)는 게시글 번호, 댓글 내용, 댓글 작성자를 JSON 문자열로 전송한다.
	// 서버(컨트롤러)는 JSON 문자열을 파라미터로 전달받는다.
	// @RequestBody는 전달받은 JSON 문자열을 객체로 변환한다.
	// 객체로 변환된 JSON은 CommentDTO 클래스의 객체인 params에 매핑(바인딩)된다.
	@RequestMapping(value = { "/comments", "/comments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDTO params)
	{
		JsonObject jsonObj = new JsonObject();
		
		try
		{
			boolean isRegistered = commentService.registerComment(params);
			jsonObj.addProperty("result", isRegistered);
			
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였다람쥐.");
			
		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였다.");
		}
		return jsonObj;
	}
	
	// @DeleteMapping :: HTTP 요청 메서드 중 DELETE를 의미 (실제로 댓글을 삭제하지 않지만, URI의 구분을 위해 @DeleteMapping을 선언)
	@DeleteMapping(value = "/comments/{idx}")
	// @PathVariable :: REST 방식에서 리소스를 표현, @PathVariable은 URI에 파라미터로 전달받을 변수를 지정 ("/comments/{idx}" URI의 {idx}는 댓글 번호(PK)를 의미하며, @PathVariable의 idx와 매핑(바인딩))
	public JsonObject deleteComment(@PathVariable("idx") final Long idx)
	{
		// 1. JSON 객체 생성
		JsonObject jsonObj = new JsonObject();
		
		try
		{
			// 2. CommentService 의 deleteComment 메서드의 실행 결과를 JSON 객체에 저장
			boolean isDeleted = commentService.deleteComment(idx);
			jsonObj.addProperty("result", isDeleted);
		
		// 각 catch 문에 해당되는 예외가 발생하면 예외 메시지를 JSON 객체에 저장
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");
			
		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}
		// JSON 객체를 리턴
		return jsonObj;
	}
	
	// @PathVariable :: @RequestParam과 유사한 기능을 하며, REST 방식에서 리소를 표현할 때 사용
    // @PathVariable 은 URI에 파라미터로 전달받을 변수 지정
    // "/comments/{insurance}" URI의 {insurance}는 게시글 번호를 의미하며, @PathVarialbe의 insuranceIdx와 매핑(바인딩)
	
	// @ModelAttribute :: 파라미터로 전달받은 객체를 자동으로 화면(뷰)로 전달
    // 특정 게시글에 등록된 댓글을 조회
	// 추후 댓글 목록의 페이징 처리에 활용
	// 로직 :: getCommentList 메서드를 호출한 결과를 commentList에 저장
	
	@GetMapping(value = "/comments/{insuranceIdx}")
	public List<CommentDTO> getCommentList(@PathVariable("insuranceIdx") Long insuranceIdx, @ModelAttribute("params") CommentDTO params)
	{
		List<CommentDTO> commentList = commentService.getCommentList(params);
		return commentList;
	}
}