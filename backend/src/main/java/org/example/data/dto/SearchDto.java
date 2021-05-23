package org.example.data.dto;


import lombok.Data;

@Data
public class SearchDto {
    String address;
    String searchQuery;
    Double minPrice;
    Double maxPrice;
    Long  categoryId;
}
