package ee.avastaeesti.gameback.persistence.question;

import ee.avastaeesti.gameback.controller.location.dto.LocationsInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationInfoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "locationName", target = "locationName")
    LocationsInfo toLocationInfo(Question location);


    List<LocationsInfo> toLocationInfos(List<Question> locations);
}
