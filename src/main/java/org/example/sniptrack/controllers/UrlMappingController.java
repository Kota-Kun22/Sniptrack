package org.example.sniptrack.controllers;

import lombok.AllArgsConstructor;
import org.example.sniptrack.dtos.UrlMappingDTO;
import org.example.sniptrack.models.User;
import org.example.sniptrack.service.UrlMappingService;
import org.example.sniptrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {


    private UserService userService;
    private final UrlMappingService urlMappingService;

    //{"originalUrl":"https://example.com"}
    //https://abc.com/QN7XOa0a --> https://example.com
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request, Principal principal){

        String originalUrl = request.get("originalUrl");
        User user = userService.findByUserName(principal.getName());
        UrlMappingDTO urlMappingDTO= urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlMappingDTO);
    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUsersAllUrls(Principal principal){

        User user = userService.findByUserName(principal.getName());
        List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);

    }
}
