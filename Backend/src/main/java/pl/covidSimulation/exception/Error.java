package pl.covidSimulation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Error {

    private Integer code;
    private String message;
    private Date errorTime;
}
