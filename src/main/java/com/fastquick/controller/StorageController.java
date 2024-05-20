package com.fastquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/storage")
public class StorageController {

    @GetMapping("/storageList")
    public String storageList() {
    	return "storage/storageList";
    }
    
    @GetMapping("/storageRequest")
    public String storageRequest() {
    	return "storage/storageRequest";
    }
}
