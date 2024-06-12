package com.miguel.project_travel.domain.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
@Builder
public class Role {

    @Field(name = "granted_authorities")
    private Set<String> grantedAuthorities;
}