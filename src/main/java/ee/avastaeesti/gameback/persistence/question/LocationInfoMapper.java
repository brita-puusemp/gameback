package ee.avastaeesti.gameback.persistence.question;

import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationInfoMapper {


    @Mapping(source="id", target = "id")
    @Mapping(source="locationName", target = "locationName")
    LocationInfo toLocationInfo(Question location);


    List<LocationInfo> toLocationInfos(List<Question> locations);
}

/*
@Mapping(source="id", target="id")
@Mapping(source="locationName", target="locationName")
LocationInfo toLocationInfo(Question location);
List<LocationInfo> toLocationInfos(List<Question> locations);*/
