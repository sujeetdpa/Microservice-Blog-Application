package com.assignment.aspd.commentservice.service.impl;

import com.assignment.aspd.commentservice.apis.blog.BlogApi;
import com.assignment.aspd.commentservice.apis.reaction.ReactionApi;
import com.assignment.aspd.commentservice.beans.request.CommentRequest;
import com.assignment.aspd.commentservice.beans.response.BlogResponse;
import com.assignment.aspd.commentservice.beans.response.CommentResponse;
import com.assignment.aspd.commentservice.beans.response.CommentResponseList;
import com.assignment.aspd.commentservice.exception.bad.request.InvalidDataException;
import com.assignment.aspd.commentservice.exception.not.found.CommentNotFoundException;
import com.assignment.aspd.commentservice.mapper.CommentMapper;
import com.assignment.aspd.commentservice.model.Comment;
import com.assignment.aspd.commentservice.repository.CommentRepository;
import com.assignment.aspd.commentservice.service.CommentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    private BlogApi blogApi;

    private ReactionApi reactionApi;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, BlogApi blogApi, ReactionApi reactionApi) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.blogApi = blogApi;
        this.reactionApi = reactionApi;
    }

    @Override
    public CommentResponseList getBlogComments(Long blogId) {
        //Fetching all the comments for a given blog id
        List<Comment> comments = commentRepository.findByBlogId(blogId);
        if (comments.isEmpty()){
            throw new CommentNotFoundException("Comments not found for the blog");
        }
        List<CommentResponse> commentResponses = comments.stream().map(commentMapper::map).collect(Collectors.toList());
        CommentResponseList responseList=new CommentResponseList();
        responseList.setNumOfItems(commentResponses.size());
        responseList.setCommentResponses(commentResponses);
        return responseList;
    }

    @Override
    public CommentResponse newComment(CommentRequest commentRequest) {
        //Contacting blog service to check if a blog exists with the given blog id
        BlogResponse blogResponse = blogApi.getBlog(commentRequest.getBlogId()).orElseThrow(() -> new InvalidDataException("Invalid Blog id"));

        //Creating a comment if the blog id valid
        Comment comment = commentMapper.map(commentRequest);
        comment.setBlogId(blogResponse.getId());
        Comment savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = commentMapper.map(savedComment);
        return commentResponse;
    }

    @Override
    public CommentResponse getComment(Long id) {
        //Getting a comment with given id from the repository
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
        CommentResponse commentResponse = commentMapper.map(comment);
        return commentResponse;
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        //Updating the comment
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
        Optional.ofNullable(commentRequest.getText()).ifPresent(comment::setText);
        Comment updatedComment = commentRepository.save(comment);
        CommentResponse commentResponse = commentMapper.map(updatedComment);
        return commentResponse;
    }

    @Override
    public void deleteCommentsOfBlog(Long blogId) {
        //Deleting the comments for the given blog id
        List<Comment> comments = commentRepository.findByBlogId(blogId);

        //Deleting all the reactions that are associated with the comments
        comments.stream().forEach(comment -> reactionApi.deleteByTargetTypeAndTargetId(comment.getId(), "COMMENT"));
        commentRepository.deleteAll(comments);
    }
}
