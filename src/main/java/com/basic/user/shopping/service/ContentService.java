package com.basic.user.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.basic.user.shopping.model.Content;
import com.basic.user.shopping.model.User;
import com.basic.user.shopping.model.dto.ContentDto;
import com.basic.user.shopping.repository.ContentRepository;
import com.basic.user.shopping.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public Content createContent(ContentDto contentDto, String createdByUsername) {
        User createdBy = userRepository.findByUsername(createdByUsername)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Content content = new Content();
        content.setTitle(contentDto.getTitle());
        content.setDescription(contentDto.getDescription());
        content.setPrice(contentDto.getPrice());
        content.setCreatedBy(createdBy);

        return contentRepository.save(content);
    }

    public List<Content> getAllContents(int page, int size, String sortBy) {
        var pageable = PageRequest.of(page - 1, size);
        return contentRepository.findAll(pageable).getContent();
    }

    public Content updateContent(Long id, Content updatedContent) {
        return contentRepository.findById(id)
                .map(existingContent -> {
                    existingContent.setTitle(updatedContent.getTitle());
                    existingContent.setDescription(updatedContent.getDescription());
                    existingContent.setPrice(updatedContent.getPrice());
                    return contentRepository.save(existingContent);
                })
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
