package org.seckill.exception;

public class RepeatKillException extends SecKillException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7588706798824576584L;

	public RepeatKillException(String message) {
		super(message);
	}
	
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
