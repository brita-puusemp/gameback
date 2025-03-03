package ee.avastaeesti.gameback.controller.login;

import ee.avastaeesti.gameback.service.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    @Operation(summary = "Sisselogimine Tagastab userId ja roleName",
            description = """
                    Süsteemist otsitakse username ja password abil kasutajat, kelle konto on ka aktiivne.
                    Kui vastet ei leita vistakse viga errorCode'ga 111""")
//error kood võetud pangast
    public void login(@RequestParam String username, @RequestParam String password) {
        loginService.login(username, password);
    }
}
