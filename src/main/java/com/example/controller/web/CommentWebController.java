package com.example.controller.web;

import com.example.model.comment.CommentRequest;
import com.example.model.comment.CommentResponse;
import com.example.model.comment.CommentUpdate;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/7")
public class CommentWebController {
    private final CommentService commentService;

    @GetMapping("/all")
    public String getAllComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "allCommentsPage";
    }

    @GetMapping("/create")
    public String goToCreateCommentPage() {
        return "createCommentPage";
    }

    @PostMapping("/create")
    public String createComment(@ModelAttribute(value = "commentRequest") CommentRequest commentRequest, Model model) {
        CommentResponse comment = commentService.createComment(commentRequest);
        model.addAttribute("comment", comment);
        return "commentCreatedPage";
    }

    @GetMapping("/update")
    public String goToUpdateCommentPage(@ModelAttribute(value = "commentId") int commentId, Model model) {
        model.addAttribute("commentId", commentId);
        return "updateCommentPage";
    }

    @PostMapping("/update")
    public String updateComment(@ModelAttribute(value = "commentUpdate") CommentUpdate commentUpdate, Model model) {
        CommentResponse comment = commentService.updateCommentById(commentUpdate.getId(), commentUpdate);
        model.addAttribute("comment", comment);
        return "commentUpdatedPage";
    }

    @PostMapping("/delete")
    public String deleteComment(@ModelAttribute(value = "commentId") int commentId, Model model) {
        commentService.deleteComment(commentId);
        model.addAttribute("comments", commentService.getAllComments());
        return "allCommentsPage";
    }
}