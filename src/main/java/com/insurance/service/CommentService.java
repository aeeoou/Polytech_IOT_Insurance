package com.insurance.service;

import java.util.List;

import com.insurance.domain.CommentDTO;

public interface CommentService
{
	public boolean registerComment(CommentDTO params);
	public boolean deleteComment(Long idx);
	public List<CommentDTO> getCommentList(CommentDTO params);
}
