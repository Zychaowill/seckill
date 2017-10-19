package org.seckill.exception;

public class SecKillException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2602986669330433593L;

	public SecKillException(String message) {
		super(message);
	}

	public SecKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
