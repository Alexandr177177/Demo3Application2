package com.example.demo.restcontrollers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.request.MailRequest;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.FileService;
import com.example.demo.services.MailSenderService;
import com.example.demo.services.rabbitMQ.EmployeeAmqpProducerService;
import com.example.demo.services.rabbitMQ.ProjectAmqpProducerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final MailSenderService mailSender;
    private final EmployeeService service;
    private final FileService fileService;
    private final EmployeeAmqpProducerService amqpProducerService;


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        try {
            EmployeeDto employee = service.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-name")
    public ResponseEntity<EmployeeDto> getEmployeeByName(@RequestParam String name) {
        try {
            EmployeeDto employee = service.getEmployeeByName(name);
            return ResponseEntity.ok(employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-mail")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam String mail) {
        try {
            EmployeeDto employee = service.getEmployeeByEmail(mail);
            return ResponseEntity.ok(employee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }

//    @PostMapping("/{id}/xls")
//    public ResponseEntity<String> createXLSFile(@PathVariable Integer id) {
//        EmployeeDto employee = service.getEmployeeById(id);
//
//        if (employee != null) {
//            mailSender.createXLSFile(employee);
//            return ResponseEntity.ok("File " + employee.getId() + " created");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/{id}/doc")
//    public ResponseEntity<String> createDocFile(@PathVariable Integer id) {
//        EmployeeDto employee = service.getEmployeeById(id);
//        if (employee != null) {
//            mailSender.createDOCFile(employee);
//            return ResponseEntity.ok("File " + employee.getId() + " created");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
 //   }

    @PostMapping("/{id}/attachmentMail")
    public ResponseEntity<String> sendEmailWithAttachment(
            @PathVariable Integer id,
            @RequestBody @Valid MailRequest mailRequest
    ) {
        EmployeeDto employee = service.getEmployeeById(id);
        mailSender.sendMailWithAttachment(
                mailRequest.getMailAddress(),
                "resume of " + employee.getName(),
                "Вы можите ознакомиться с содержанием письма",
                fileService.createFile(employee, mailRequest.getFormatFile())
        );
        return ResponseEntity.ok("Email had sent to " + employee.getName());
    }

    @PostMapping ("/{id}/rabbit-email")
    public ResponseEntity<String> sendEmailToRabbit(
            @PathVariable Integer id
    ){
        EmployeeDto employee = service.getEmployeeById(id);
        amqpProducerService.sendMessage(employee);
        return ResponseEntity.ok("Email about " + employee.getName());
    }
}

