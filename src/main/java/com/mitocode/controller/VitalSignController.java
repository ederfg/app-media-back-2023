package com.mitocode.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.VitalSignDTO;
import com.mitocode.model.VitalSign;
import com.mitocode.service.iVitalSignService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vital-sign")
@RequiredArgsConstructor
public class VitalSignController {

	private final iVitalSignService service;// = new VitalSignService();
	@Qualifier("defaultMapper")
	private final ModelMapper modelMapper;
	
    @GetMapping
    public ResponseEntity<List<VitalSignDTO>> findAll(){
        List<VitalSignDTO> list = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	@GetMapping("/{id}")
	public ResponseEntity<VitalSignDTO> findById(@PathVariable("id") Integer id) {
		VitalSignDTO dto = this.convertToDto(service.findById(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VitalSignDTO> save(@Valid @RequestBody VitalSignDTO dto) {
		VitalSign obj = service.save(this.convertToEntity(dto));
		// localhost:8080/VitalSigns/3
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdVitalSign()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<VitalSignDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody VitalSignDTO dto) {
		dto.setIdVitalSign(id);
		VitalSign obj = service.update(this.convertToEntity(dto), id);
		return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Min(1) @PathVariable("id") Integer id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<VitalSignDTO>> listPage(Pageable pageable) {
		Page<VitalSignDTO> page = service.listPage(pageable).map(p -> modelMapper.map(p, VitalSignDTO.class));
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	private VitalSignDTO convertToDto(VitalSign obj) {
		return modelMapper.map(obj, VitalSignDTO.class);
	}

	private VitalSign convertToEntity(VitalSignDTO dto) {
		return modelMapper.map(dto, VitalSign.class);
	}

}
