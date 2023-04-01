package com.joalvarez.wharehousecontrol.general.exception;

import com.joalvarez.wharehousecontrol.general.HasLogger;
import com.joalvarez.wharehousecontrol.general.data.dto.ResponseDTO;
import com.joalvarez.wharehousecontrol.general.data.type.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException implements HasLogger {

    @ExceptionHandler
    public ResponseEntity<ResponseDTO> handler(GenericException e) {
        this.error(e.getMessage());

        ResponseDTO dto = new ResponseDTO(e.getResponseCode());

        List<String> details = new ArrayList<>();
        details.add(e.getMessage());

        dto.setDetails(details);

        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handler(MethodArgumentNotValidException e) {
        this.error("Error : " +
                e.getParameter().getMember().getDeclaringClass().getName() + "." +
                e.getParameter().getMethod().getName() + "("+ e.getBindingResult().getObjectName() + ")");

        ResponseDTO dto = new ResponseDTO();

        dto.setCode(ResponseCode.INCORRECT_DATA.code());
        dto.setMessage(ResponseCode.INCORRECT_DATA.message());
        List<String> errors =
                e.getBindingResult().getAllErrors().stream().filter(error -> error instanceof FieldError).map(error -> (FieldError) error)
                        .map((error) -> "The field {" + error.getField() + "} is invalid for the value {" + error.getRejectedValue() +
                                "} with the following cause: " + error.getDefaultMessage()).collect(Collectors.toList());
        dto.setDetails(errors);


        return ResponseEntity.badRequest().body(dto);

    }
}
