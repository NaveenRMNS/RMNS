package com.punchershop.exception;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Naveen
 *
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizeException extends RuntimeException {
	
	
	private static final long serialVersionUID = 5242985939761301254L;
	
	protected static MessageSourceAccessor messageSourceAccessor = SpringSecurityMessageSource.getAccessor();
	
	public UnauthorizeException() {
		super(messageSourceAccessor.getMessage("AbstractAcessDecisionManger.accessDenied", "Acess is denied"));
	}

	public UnauthorizeException(String message) {
		super(message);
	}
	
}
