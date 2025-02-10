package com.basic.user.shopping.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.basic.user.shopping.model.Content;
import com.basic.user.shopping.model.Purchase;
import com.basic.user.shopping.model.User;
import com.basic.user.shopping.model.dto.PurchaseDto;
import com.basic.user.shopping.repository.ContentRepository;
import com.basic.user.shopping.repository.PurchaseRepository;
import com.basic.user.shopping.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Transactional
    public void createPurchase(PurchaseDto purchaseDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Content content = contentRepository.findById(purchaseDto.getContentId())
                .orElseThrow(() -> new IllegalArgumentException("Content not found"));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setContent(content);
        purchase.setPrice(content.getPrice());

        purchaseRepository.save(purchase);
    }

    public List<Purchase> getPurchases(String username, int page, int size) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var pageable = PageRequest.of(page - 1, size);
        return purchaseRepository.findAllByUserId(user.getId(),pageable).getContent();
    }
}
