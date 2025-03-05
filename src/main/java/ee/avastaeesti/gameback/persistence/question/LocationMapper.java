package ee.avastaeesti.gameback.persistence.question;

import ee.avastaeesti.gameback.controller.location.dto.NewLocation;
import ee.avastaeesti.gameback.status.Status;
import ee.avastaeesti.gameback.util.BytesConverter;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface LocationMapper {
    @Mapping(source = "locationName", target = "locationName")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "clue", target = "clue")
    @Mapping(source = "imageData", target = "imageData", qualifiedByName = "toBytes")
    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    Question toQuestion(NewLocation newLocation);

    @Named("toBytes")
    static byte[] toBytes(String imageData) {
        return BytesConverter.stringToBytesArray(imageData);
    }

}