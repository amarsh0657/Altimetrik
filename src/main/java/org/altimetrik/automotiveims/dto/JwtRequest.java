package org.altimetrik.automotiveims.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {
    private String username;
    private String password;
}
