package tr.com.bg.interview.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.bg.interview.model.Comment;
import tr.com.bg.interview.model.dto.CommentDto;
import tr.com.bg.interview.service.CommentService;

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
