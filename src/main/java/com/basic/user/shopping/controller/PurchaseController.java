package com.basic.user.shopping.controller;

import com.basic.user.shopping.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basic.user.shopping.model.Purchase;
import com.basic.user.shopping.model.dto.PurchaseDto;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<String> createPurchase(@Valid @RequestBody PurchaseDto purchaseDto, @RequestParam String username) {
        purchaseService.createPurchase(purchaseDto, username);
        return ResponseEntity.ok("Purchase created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getPurchases(@RequestParam String username,
                                                       @RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(purchaseService.getPurchases(username, page, size));
    }

}
