package com.java.springboot.organization_service.controller;

import com.java.springboot.organization_service.dto.OrganizationDto;
import com.java.springboot.organization_service.entity.Organization;
import com.java.springboot.organization_service.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/org")
@AllArgsConstructor
public class Organization_controller {
    private OrganizationService orgService;
    @PostMapping("create")
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization=orgService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("show/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable String organizationCode){
       OrganizationDto organizationDto=orgService.getOrganizationByCode(organizationCode);
       return new ResponseEntity<>(organizationDto,HttpStatus.OK);
    }
}
