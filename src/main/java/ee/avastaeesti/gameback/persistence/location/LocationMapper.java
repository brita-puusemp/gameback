package ee.avastaeesti.gameback.persistence.location;

import ee.avastaeesti.gameback.controller.location.dto.LocationDto;
import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.status.Status;
import ee.avastaeesti.gameback.util.BytesConverter;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface LocationMapper {
    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "clue", target = "clue")
    @Mapping(source = "imageData", target = "imageData", qualifiedByName = "toBytes")
    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    Location toQuestion(LocationDto locationDto);

    @Named("toBytes")
    static byte[] toBytes(String imageData) {
        return BytesConverter.stringToBytesArray(imageData);
    }

    @Mapping(source = "id", target = "locationId")
    @Mapping(source = "name", target = "locationName")
    LocationInfo toLocationInfo(Location location);


    List<LocationInfo> toLocationInfos(List<Location> locations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "locationName", target = "name")
    @Mapping(source = "imageData", target = "imageData", qualifiedByName = "toBytes")
    Location updateLocation(LocationDto locationDto, @MappingTarget Location location);

}