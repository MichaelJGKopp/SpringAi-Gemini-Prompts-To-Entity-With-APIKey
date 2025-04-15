package io.michaeljgkopp.github.sb3springaiprompttoentity.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Book(
     String title,
     List<String> authors,
     String isbn13,
     String isbn10,
     String publisher,
     LocalDate publicationDate,
     String edition,
     BigDecimal price,
     BigDecimal discountedPrice,
     String format, // Hardcover, Paperback, Kindle, Audiobook
     int pageCount,
     List<String> categories,
     String series,
     String language,
     String coverImageUrl,
     String description,
     double averageRating,
     int reviewCount,
     String dimensions,
     String weight) {}