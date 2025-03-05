package ee.avastaeesti.gameback.persistence.question;

import ee.avastaeesti.gameback.controller.location.dto.NewLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {
    @Mapping(source = "locationName", target = "locationName")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "clue", target = "clue")
    @Mapping(source = "imageData", target = "imageData")
//@Mapping(expression = "", target = "")
    NewLocation toQuestion(NewLocation newLocation);

}