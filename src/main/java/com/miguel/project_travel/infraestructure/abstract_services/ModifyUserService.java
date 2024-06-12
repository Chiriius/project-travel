package com.miguel.project_travel.infraestructure.abstract_services;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ModifyUserService {

    Map<String,Boolean> enabled(String username);
    Map<String, Set<String>> removeRol(String username, String role);
    Map<String,Set<String>> addRole(String username, String role);
}
