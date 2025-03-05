package ee.avastaeesti.gameback.persistence.user;

import ee.avastaeesti.gameback.controller.login.dto.LoginResponse;
import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.status.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {Status.class})
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.roleName", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "username", target = "username")
    @Mapping(expression = "java(Status.ACTIVE.getCode())", target = "status")
    User toUser(NewUser newUser);

}