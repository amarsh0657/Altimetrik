package org.altimetrik.automotiveims.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private String role;
    private String name;
    private Long userId;
}
