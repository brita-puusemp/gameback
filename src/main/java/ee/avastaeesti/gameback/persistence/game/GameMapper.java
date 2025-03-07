package ee.avastaeesti.gameback.persistence.game;

import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.status.Status;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status .class})
public interface GameMapper {

    @Mapping(source = "gameName", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "timePerLocation", target = "timePerLocation")
    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    Game toGame(NewGame newGame);

}