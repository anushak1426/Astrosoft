package app.astrosoft.api.dto;

import java.io.Serializable;

import app.astrosoft.consts.AstrosoftServiceErrors;

public class ApiResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status = "SUCCESS";
	private T response;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public AstrosoftServiceErrors getError() {
		return error;
	}
	public void setError(AstrosoftServiceErrors error) {
		this.error = error;
	}
	private AstrosoftServiceErrors error;

}
