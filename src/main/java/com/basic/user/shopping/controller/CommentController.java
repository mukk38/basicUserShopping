package com.basic.user.shopping.controller;


import com.basic.user.shopping.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basic.user.shopping.model.Comment;
import com.basic.user.shopping.model.dto.CommentDto;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{contentId}")
    public ResponseEntity<String> addComment(@PathVariable Long contentId,
                                             @Valid @RequestBody CommentDto commentDto,
                                             @RequestParam String username) {
        commentService.addComment(contentId, commentDto, username);
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long contentId,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(commentService.getComments(contentId, page, size));
    }
}
