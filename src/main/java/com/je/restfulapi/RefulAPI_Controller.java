package com.je.restfulapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RefulAPI_Controller {

    private final List<String> TestList = new ArrayList<>();

    @PostConstruct
    private void AddData(){
        TestList.add("JE-Chen");

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> Main_Page_Get(@PathVariable("id") String id){
        if(!TestList.contains(id)){
            System.out.println("404 Not Found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Hello "+id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> Main_Page_Post(@PathVariable("id") String id){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        TestList.add(id);
        System.out.println(id);
        return ResponseEntity.created(location).body(id);
    }
}
