package com.punchershop.response;

import java.io.Serializable;

/**
 * @author Naveen
 *
 */
public class PResponse implements Serializable {

	private static final long serialVersionUID = 7793142713169696056L;

	private String message;

	public PResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
