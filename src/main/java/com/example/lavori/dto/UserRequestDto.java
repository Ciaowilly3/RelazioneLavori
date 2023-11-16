package com.example.lavori.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRequestDto {
    private String userName;
    private String lavoroName;
    private Long userId;
}
