package com.bunny.review_service.review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    boolean createReview(UUID companyId, Review review);

    List<Review> findAllReviews(UUID companyId);

    Review findReviewById(UUID id);

    Review updateReviewById(UUID id, Review review);

    boolean deleteReviewById(UUID id);
}
