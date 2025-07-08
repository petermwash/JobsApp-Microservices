package com.bunny.review_service.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<String> createReview(
            @RequestParam UUID companyId,
            @RequestBody Review review
    ) {
        boolean reviewCreated = reviewService.createReview(companyId, review);

        if (reviewCreated) return ResponseEntity.ok("Review created!");

        return new ResponseEntity<>("Review could not be created!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAllReviews(
            @RequestParam UUID companyId
    ) {
        return ResponseEntity.ok(reviewService.findAllReviews(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findReviewById(
            @PathVariable UUID id
    ) {
        Review review = reviewService.findReviewById(id);

        if (review != null) return ResponseEntity.ok(review);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReviewById(
            @PathVariable UUID id,
            @RequestBody Review review
    ) {
        Review updatedReview = reviewService.updateReviewById(id, review);

        if (updatedReview != null) return ResponseEntity.ok(updatedReview);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(
            @PathVariable UUID id
            ) {
        boolean reviewDeleted = reviewService.deleteReviewById(id);

        if (reviewDeleted) return ResponseEntity.ok("Review Deleted!");

        return new ResponseEntity<>("Resource not found!", HttpStatus.NOT_FOUND);
    }
}
