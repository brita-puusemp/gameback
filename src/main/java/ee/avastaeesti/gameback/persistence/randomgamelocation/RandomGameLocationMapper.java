package ee.avastaeesti.gameback.persistence.randomgamelocation;

import ee.avastaeesti.gameback.controller.randomgame.dto.NextRandomLocation;
import ee.avastaeesti.gameback.util.BytesConverter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RandomGameLocationMapper {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "location.longitude", target = "longitude")
    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.clue", target = "clue")
    @Mapping(source = "location.imageData", target = "imageData", qualifiedByName = "toString")
    @Mapping(source = "timeStart", target = "timeStart")
    NextRandomLocation toNextRandomLocation(RandomGameLocation randomGameLocation);


    @Named("toString")
    static String toString(byte[] imageData) {
        return BytesConverter.bytesArrayToString(imageData);
    }

}