package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.insurance.domain.CommentDTO;
import com.insurance.service.CommentService;

@RestController                                                // @RestController :: 선언된 컨트롤러의 모든 메서드는 화면이 아닌, 리턴 타입에 해당하는 데이터 자체를 리턴
public class CommentController
{
	@Autowired
	private CommentService commentService;
	
	// 게시글의 경우, 하나의 URI로 생성(INSERT)과 수정(UPDATE) 처리가 가능하지만, 'REST API'는 설계 규칙을 지켜야 하기 때문에 URI를 구분함
	 // value "/comments" :: 새로운 댓글 등록, "/comments/{idx} :: 댓글 테이블의 PK인 댓글 번호(idx)에 해당하는 댓글 수정
	  // RequestMethod.POST  :: HTTP 요청 메서드 중 POST를 의미하며, @PostMapping과 유사
	  // RequestMethod.PATCH :: HTTP 요청 메서드 중 PATCH를 의미하며, @PatchMapping과 유사 
	@RequestMapping(value = { "/comments", "/comments/{idx}" }, method = {RequestMethod.POST, RequestMethod.PATCH })
	// RequestBody :: REST 방식의 처리에 사용. 파라미터 앞에 @RequestBody가 지정되면, 파라미터로 전달받은 JSON 문자열을 객체로 변환
	 // 클라이언트(사용자)는 게시글 번호, 댓글 내용, 댓글 작성자를 JSON 문자열로 전송한다.
	 // 서버(컨트롤러)는 JSON 문자열을 파라미터로 전달받는다.
	 // @RequestBody는 전달받은 JSON 문자열을 객체로 변환한다.
	 // 객체로 변환된 JSON은 CommentDTO 클래스의 객체인 params에 매핑(바인딩)된다.
	public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDTO params)
	{                                                                                                                                   
		JsonObject jsonobj = new JsonObject();
		
		try
		{
			// 결과를 저장할 JOSN 객체 생성. idx를 파라미터로 전달받으면 댓글 수정
			// isRegistered
			// CommentService 의 registerComment 메서드를 실행한 결과 저장
			// 댓글의 생성 또는 수정이 실행되면 true를, 실행되지 않으면 false를 저장
			// 메서드의 실행 결과를 "result" 라는 이름의 프로퍼터로 JSON 객체에 추가해서 리턴
			boolean isRegistered = commentService.registerComment(params);
			jsonobj.addProperty("result", isRegistered);
			
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");
			
		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}
		return jsonObj;
	}
	
	@GetMapping(value = "/comments/{insuranceIdx}")
	public List<CommentDTO> getCommentList(@PathVariable("insuranceIdx") Long insuranceIdx, @ModelAttribute("params") CommentDTO params)   // @PathVariable :: @RequestParam과 유사한 기능을 하며, REST 방식에서 리소를 표현할 때 사용
	{                                                                                                                                      //               :: @PathVariable 은 URI에 파라미터로 전달받을 변수 지정
		List<CommentDTO> commentList = commentService.getCommentList(params);                                                              //               :: "/comments/{insurance}" URI의 {insurance}는 게시글 번호를 의미하며, @PathVarialbe의 insuranceIdx와 매핑(바인딩)
		return commentList;                                                                                 // @ModelAttribute :: 파라미터로 전달받은 객체를 자동으로 화면(뷰)로 전달
	}                                                                                                       //                 :: 특정 게시글에 등록된 댓글을 조회
}                                                                                                           //                 :: 추후 댓글 목록의 페이징 처리에 활용

                                                                                                            // 로직 :: getCommentList 메서드를 호출한 결과를 commentList에 저장