package com.miguel.project_travel.api.controllers;

import com.miguel.project_travel.api.models.request.TourRequest;
import com.miguel.project_travel.api.models.responses.TourResponse;
import com.miguel.project_travel.infraestructure.abstract_services.ITourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {
    private  final ITourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse>post(@RequestBody TourRequest request){
       return ResponseEntity.ok(this.tourService.create(request));

    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse>post(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));

    }

    @DeleteMapping (path = "{id}")
    public ResponseEntity<TourResponse>delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
