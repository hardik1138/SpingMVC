package com.narola.spring;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Book {

    private String name;
    private String author;
    private String publisher;
    private double price;
    private MultipartFile file;
    private List<MultipartFile> file2;

    public List<MultipartFile> getFile2() {
        return file2;
    }

    public void setFile2(List<MultipartFile> file2) {
        this.file2 = file2;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
