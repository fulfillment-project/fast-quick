package com.fastquick.data.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberRequestDTO {
    private String id;
    private String pwd;
}
