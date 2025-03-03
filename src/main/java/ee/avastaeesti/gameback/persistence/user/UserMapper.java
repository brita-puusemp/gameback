package ee.avastaeesti.gameback.persistence.user;

import ee.avastaeesti.gameback.controller.login.dto.LoginResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.roleName", target = "roleName")
    LoginResponse toLoginResponse(User user);

}