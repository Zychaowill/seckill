package org.seckill.enums;

public enum SecKillStateEnum {
	SUCCESS(1, "SECKILL_SUCCESS"), END(0, "SECKILL_END"), REPEAT(-1, "REPEAT_SECKILL"), INNER_ERROR(-2,
			"INNER_ERROR"), DATA_REWRITE(-3, "DATA_REWRITE");

	private int state;
	private String stateInfo;

	private SecKillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SecKillStateEnum stateOf(int index) {

		for (SecKillStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
