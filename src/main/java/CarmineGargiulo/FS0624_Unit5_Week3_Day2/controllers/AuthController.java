package CarmineGargiulo.FS0624_Unit5_Week3_Day2.controllers;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.EmployeesLoginDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.EmployeesTokenDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.services.AuthService;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public EmployeesTokenDTO login(@RequestBody @Validated EmployeesLoginDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message =
                    bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(
                            ", "));
            throw new BadRequestException(message);
        }
        return new EmployeesTokenDTO(authService.generateToken(body));
    }

}
