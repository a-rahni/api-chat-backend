package fr.m2i.apichat.mappers;

import fr.m2i.apichat.dtos.CanalDto;
import fr.m2i.apichat.model.Canal;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CanalMapper {
    Canal canalDtoToCanal(CanalDto canalDto);

    CanalDto canalToCanalDto(Canal canal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Canal updateCanalFromCanalDto(CanalDto canalDto, @MappingTarget Canal canal);
}
