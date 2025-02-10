package tr.com.bg.interview.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.bg.interview.model.Comment;
import tr.com.bg.interview.model.Content;
import tr.com.bg.interview.model.User;
import tr.com.bg.interview.model.dto.CommentDto;
import tr.com.bg.interview.repository.CommentRepository;
import tr.com.bg.interview.repository.ContentRepository;
import tr.com.bg.interview.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(Long contentId, CommentDto commentDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("Content not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setComment(commentDto.getComment());
        comment.setCreatedAt(new java.util.Date());

        commentRepository.save(comment);
    }

    public List<Comment> getComments(Long contentId, int page, int size) {
        var pageable = PageRequest.of(page - 1, size); // Pageable için 0 tabanlı index gerekir
        return commentRepository.findByContentId(contentId, pageable).getContent();
    }
}
