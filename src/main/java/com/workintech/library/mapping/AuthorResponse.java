package com.workintech.library.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private int id;
    private String firstName;
    private String lastName;
    private List<BookResponse> bookResponses;
}
