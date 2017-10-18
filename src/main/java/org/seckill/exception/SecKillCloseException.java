package org.seckill.exception;

public class SecKillCloseException extends SecKillException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6663623530104627806L;

	public SecKillCloseException(String message) {
		super(message);
	}

	public SecKillCloseException(String message, Throwable cause) {
		super(message, cause);
	}
}
