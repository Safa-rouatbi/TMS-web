package com.gpro.consulting.tms.mstiers.service;

import com.gpro.consulting.tms.mstiers.dto.PartieInteresseCreateDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseReadDTO;
import com.gpro.consulting.tms.mstiers.dto.PartieInteresseUpdateDTO;
import com.gpro.consulting.tms.mstiers.entity.PartieInteresseEntite;
import com.gpro.consulting.tms.mstiers.mapper.PartieInteresseMapper;
import com.gpro.consulting.tms.mstiers.repository.PartieInteresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartieInteresseService {

    private final PartieInteresseRepository repository;


    public PartieInteresseReadDTO create(PartieInteresseCreateDTO dto) {
        PartieInteresseEntite entity = PartieInteresseMapper.toEntity(dto);
        PartieInteresseEntite saved = repository.save(entity);
        return PartieInteresseMapper.toReadDTO(saved);
    }


    public List<PartieInteresseReadDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(PartieInteresseMapper::toReadDTO)
                .toList();
    }


    public PartieInteresseReadDTO getById(Long id) {
        return repository.findById(id)
                .map(PartieInteresseMapper::toReadDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Partie intéressée non trouvée avec id " + id));
    }


    public List<PartieInteresseReadDTO> getByType(String typeClient) {
        return repository.findByTypeClient(typeClient)
                .stream()
                .map(PartieInteresseMapper::toReadDTO)
                .toList();
    }


    public PartieInteresseReadDTO update(Long id, PartieInteresseUpdateDTO dto) {
        PartieInteresseEntite entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Partie intéressée non trouvée avec id " + id));

        PartieInteresseMapper.applyUpdate(entity, dto);

        PartieInteresseEntite saved = repository.save(entity);
        return PartieInteresseMapper.toReadDTO(saved);
    }


    public void softDelete(Long id) {
        PartieInteresseEntite entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Partie intéressée non trouvée avec id " + id));

        entity.setActif(false);
        entity.setBlSuppression(true);
        entity.setDateSuppression(LocalDateTime.now());

        repository.save(entity);
    }
}
