package com.narola.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/v1/orders/{orderId}")
    public ResponseEntity<String> testv1(@PathVariable int orderId) {
        if (orderId > 100) {
            throw new ResourceNotFoundException("OrderID=" + orderId);
        }
        return ResponseEntity.ok().body("Okay.");
    }

    @PostMapping("/v1/upload")
    public ResponseEntity<String> testv1(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok().body("Okay." + file.getOriginalFilename());
    }

    @PostMapping("/v2/upload")
    public ResponseEntity<String> testv2(@RequestPart("file") List<MultipartFile> fileList) {
        return ResponseEntity.ok().body("Okay.");
    }

    @PostMapping("/v3/upload")
    public ResponseEntity<String> testv3(@RequestParam Map<String, MultipartFile> fileMap) {
        return ResponseEntity.ok().body("Okay.");
    }

    @PostMapping("/v4/upload")
    public ResponseEntity<String> testv4(Book book) {
        return ResponseEntity.ok().body("Okay.");
    }

    @GetMapping("/v5/rest")
    public ResponseEntity<String> testv5() {
        RestTemplate restTemplate = new RestTemplate();
        return ResponseEntity.ok().body("Okay.");
    }


}
