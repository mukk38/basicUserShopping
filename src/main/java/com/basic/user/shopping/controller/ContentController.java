package com.basic.user.shopping.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.basic.user.shopping.model.Content;
import com.basic.user.shopping.model.dto.ContentDto;
import com.basic.user.shopping.service.ContentService;

import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping
    public ResponseEntity<List<Content>> getAllContents(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(contentService.getAllContents(page, size, sortBy));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Content> createContent(@Valid @RequestBody ContentDto contentDto, @RequestParam String username) {
        Content content = contentService.createContent(contentDto, username);
        return ResponseEntity.ok(content);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content content) {
        return ResponseEntity.ok(contentService.updateContent(id, content));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}