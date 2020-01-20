package com.example.refuse.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.example.refuse.dto.Error;
import com.example.refuse.dto.Response;
import com.example.refuse.dto.ResponseCodes;

@Component
public class ResponseUtil {

    @Autowired
    AppUtils appUtils;

    public Response getErrorResponse(Error err) {
        Response response = new Response();
        List<Error> errors = new ArrayList<Error>();
        errors.add(err);
        response.setErrors(errors);

        return response;
    }

    //TODO write unit tests
    public ResponseEntity<Response> returnErrorsResponse(List<Error> errs, HttpStatus status)
    {
        Response response = new Response();
        response.setErrors(errs);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Response> returnError(Error err, HttpStatus status) {
    	return ResponseEntity.status(status).body(getErrorResponse(err));
    }
    
    public ResponseEntity<Response> returnSystemError()
    {
        Error err = new Error(ResponseCodes.SYSTEM_ERROR, ConstantsUtil.SYSTEM_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(err));
    }

    public ResponseEntity<Response> returnNotFound(String id, String entity)
    {
        Error err = new Error(ResponseCodes.NOT_FOUND, appUtils.getNotFoundMessage(entity, id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getErrorResponse(err));
    }

    public ResponseEntity<Response> returnSuccessResponse(Object data)
    {
        Response response = new Response();

        response.setResult(data);
        response.setCode(ResponseCodes.SUCCESS.code());

        return ResponseEntity.ok(response);
    }

    //TODO write unit tests
	public ResponseEntity<Response> returnValidationErrors(Errors errors)
	{
		List<Error> errList = new ArrayList<>();
		
		for(ObjectError err : errors.getAllErrors()) {
			errList.add(new Error(ResponseCodes.INVALID_REQUEST, err.getObjectName() + " - " + err.getDefaultMessage()));
		}
		return returnErrorsResponse(errList, HttpStatus.BAD_REQUEST);
	}

}
