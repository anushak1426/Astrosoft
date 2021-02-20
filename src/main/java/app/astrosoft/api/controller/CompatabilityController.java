package app.astrosoft.api.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.astrosoft.api.exception.AstrosoftException;

import app.astrosoft.api.dto.ApiRequest;
import app.astrosoft.api.dto.ApiResponse;
import app.astrosoft.beans.BirthData;
import app.astrosoft.beans.Place;
import app.astrosoft.consts.AstrosoftConstants;
import app.astrosoft.consts.Sex;
import app.astrosoft.core.Compactibility;
import app.astrosoft.core.Horoscope;

@RestController
@RequestMapping(value = "/v1")
public class CompatabilityController {

	@PostMapping(path = "/displayCompactibility", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ApiResponse<Compactibility> getDisplayCompactibility(HttpServletRequest httpRequest,
			@RequestHeader(name = AstrosoftConstants.HEADER_PARAM_HREQUESTOR_ID, required = true) String hRequestorId,
			@RequestHeader(name = AstrosoftConstants.HEADER_SESSION_ID, required = false) String hSessionId,
			@RequestBody ApiRequest request) throws AstrosoftException {
		ApiResponse<Compactibility> response = new ApiResponse<Compactibility>();
		response.setResponse(new Compactibility(getBoyHoroscope(request), getGirlHoroscope(request)));
		return response;
	}

	@PostMapping(path = "/displayCompactibilityStarsData", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ApiResponse<Compactibility> getDisplayCompactibilityWithStarsData(HttpServletRequest httpRequest,
			@RequestHeader(name = AstrosoftConstants.HEADER_PARAM_HREQUESTOR_ID, required = true) String hRequestorId,
			@RequestHeader(name = AstrosoftConstants.HEADER_SESSION_ID, required = false) String hSessionId,
			@RequestBody ApiRequest request) throws AstrosoftException {
		ApiResponse<Compactibility> response = new ApiResponse<Compactibility>();
		response.setResponse(new Compactibility(request.getBoyName(), request.getGirlName(), request.getBoyNak(), request.getGirlNak(), request.getBoyRasi(), request.getGirlRasi()));
		return response;
	}
	
	/***
	 * 
	 * @param request
	 * @return
	 */
	private Horoscope getGirlHoroscope(ApiRequest request) {
		Calendar c = Calendar.getInstance();
		c.setTime(request.getGirlHoroscope().getBirthDate());
		return new Horoscope(new BirthData(request.getGirlHoroscope().getName(), Enum.valueOf(Sex.class, request.getGirlHoroscope().getSex()), c, 
				new Place(request.getGirlHoroscope().getCity(), "Tamil Nadu", "India", request.getGirlHoroscope().getLatitudeValue(), request.getGirlHoroscope().getLongitudeValue(), Double.toString(request.getGirlHoroscope().getTimeZone()))));
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Horoscope getBoyHoroscope(ApiRequest request) {
		Calendar c = Calendar.getInstance();
		c.setTime(request.getBoyHoroscope().getBirthDate());
		return new Horoscope(new BirthData(request.getBoyHoroscope().getName(),
				Enum.valueOf(Sex.class, request.getBoyHoroscope().getSex()), c,
				new Place(request.getBoyHoroscope().getCity(), "Tamil Nadu", "India", request.getBoyHoroscope().getLatitudeValue(), request.getBoyHoroscope().getLongitudeValue(), Double.toString(request.getBoyHoroscope().getTimeZone()))));
		
	}
}