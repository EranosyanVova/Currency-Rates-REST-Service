package com.example.application.requestsdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyInfo {
    Giphy data;
    Object meta;
}
