package org.seckill.dto;

public class Exposer {

	private boolean exposed;

	private long secKillId;

	private String md5;

	private long now;

	private long start;

	private long end;

	public Exposer(boolean exposed, long secKillId, String md5) {
		this.exposed = exposed;
		this.secKillId = secKillId;
		this.md5 = md5;
	}

    public Exposer(boolean exposed, long secKillId, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.secKillId = secKillId;
        this.end = end;
    }

	public Exposer(boolean exposed, long secKillId) {
		this.exposed = exposed;
		this.secKillId = secKillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public long getSecKillId() {
		return secKillId;
	}

	public void setSecKillId(long secKillId) {
		this.secKillId = secKillId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
}
