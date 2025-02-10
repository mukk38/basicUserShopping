package tr.com.bg.interview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.bg.interview.model.Purchase;
import tr.com.bg.interview.model.dto.PurchaseDto;
import tr.com.bg.interview.service.PurchaseService;
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
