package CarmineGargiulo.FS0624_Unit5_Week3_Day2.services;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.EmployeesLoginDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Employee;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions.UnauthorizedException;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private JWT jwt;

    public String generateToken(EmployeesLoginDTO body) {
        Employee employee = employeesService.findEmployeeByUsername(body.username());
        if (bcrypt.matches(body.password(), employee.getPassword())) return jwt.genereteToken(employee);
        else throw new UnauthorizedException("Invalid credentials");
    }
}
