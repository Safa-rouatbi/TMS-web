package com.gpro.consulting.tms.mstiers.controller;

import com.gpro.consulting.tms.mstiers.dto.PartieInteresseCreateDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseReadDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseUpdateDTO;
import com.gpro.consulting.tms.mstiers.service.PartieInteresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PartieInteresseController {

    private final PartieInteresseService service;


    @PostMapping
    public ResponseEntity<PartieInteresseReadDTO> create(@RequestBody PartieInteresseCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @GetMapping
    public List<PartieInteresseReadDTO> getAll(@RequestParam(required = false) String type) {
        if (type != null && !type.isEmpty()) {
            return service.getByType(type);
        }
        return service.getAll();
    }


    @GetMapping("/{id}")
    public PartieInteresseReadDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PutMapping("/{id}")
    public PartieInteresseReadDTO update(@PathVariable Long id,
                                         @RequestBody PartieInteresseUpdateDTO dto) {
        return service.update(id, dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
