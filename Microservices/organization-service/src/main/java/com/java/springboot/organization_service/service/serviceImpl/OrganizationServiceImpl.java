package com.java.springboot.organization_service.service.serviceImpl;

import com.java.springboot.organization_service.dto.OrganizationDto;
import com.java.springboot.organization_service.entity.Organization;
import com.java.springboot.organization_service.mapper.OrganizationMapper;
import com.java.springboot.organization_service.repository.OrganizationRepository;
import com.java.springboot.organization_service.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepo;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization= OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization=organizationRepo.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization=organizationRepo.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
