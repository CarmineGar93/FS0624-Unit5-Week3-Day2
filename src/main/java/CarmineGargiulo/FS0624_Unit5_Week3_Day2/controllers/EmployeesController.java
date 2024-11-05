package CarmineGargiulo.FS0624_Unit5_Week3_Day2.controllers;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto.EmployeeDTO;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Employee;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions.BadRequestException;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Employee> getEmployees() {
        return employeesService.findAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployee(@PathVariable UUID employeeId) {
        return employeesService.findEmployeeById(employeeId);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message =
                    bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            throw new BadRequestException(message);
        }
        return employeesService.saveEmployee(body);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody @Validated EmployeeDTO body,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message =
                    bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            throw new BadRequestException(message);
        }
        return employeesService.findEmployeeByIdAndUpdate(employeeId, body);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID employeeId) {
        employeesService.findEmployeeByIdAndDelete(employeeId);
    }

    @GetMapping("/me")
    public Employee getPersonalProfile(@AuthenticationPrincipal Employee current) {
        return current;
    }

    @PutMapping("/me")
    public Employee getPersonalProfileAndUpdate(@AuthenticationPrincipal Employee employee,
                                                @RequestBody @Validated EmployeeDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message =
                    bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(
                            ", "));
            throw new BadRequestException(message);
        }
        return employeesService.findEmployeeByIdAndUpdate(employee.getEmployeeId(), body);
    }

    @PatchMapping("/me/avatar")
    public void uploadAvatar(@RequestParam("avatar") MultipartFile file, @AuthenticationPrincipal Employee current) {
        employeesService.uploadAvatar(file, current.getEmployeeId());
    }
}
