package com.java.springboot.organization_service.service;

import com.java.springboot.organization_service.dto.OrganizationDto;


public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
}
