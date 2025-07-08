package com.bunny.review_service.review.impl;

import com.bunny.review_service.review.Review;
import com.bunny.review_service.review.ReviewRepository;
import com.bunny.review_service.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public boolean createReview(UUID companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> findAllReviews(UUID companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findReviewById(UUID id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review updateReviewById(UUID id, Review review) {
        Review reviewToUpdate = reviewRepository.findById(id).orElse(null);

        if (reviewToUpdate != null) {
            if (review.getTitle() != null) reviewToUpdate.setTitle(review.getTitle());
            if (review.getReview() != null) reviewToUpdate.setReview(review.getReview());
            if (review.getRating() != null) reviewToUpdate.setRating(review.getRating());
            if (review.getAuthor() != null) reviewToUpdate.setAuthor(review.getAuthor());

            reviewRepository.save(reviewToUpdate);
            return reviewToUpdate;
        }
        return null;
    }

    @Override
    public boolean deleteReviewById(UUID id) {
        Review reviewToDelete = reviewRepository.findById(id).orElse(null);

        if (reviewToDelete != null
                && reviewRepository.existsById(id)) {
            reviewRepository.delete(reviewToDelete);
            return true;
        }

        return false;
    }
}
