package org.seckill.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.seckill.dao.SecKillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SecKillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.exception.SecKillException;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SecKillServiceImpl implements SecKillService {
	private Logger logger = LoggerFactory.getLogger(SecKillService.class);

	@Autowired
	private SecKillDao secKillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;

	private final String salt = "sdlkjs#$#$dfowierlkjafdmv232k3j@@##$";

	@Override
	public List<SecKill> getSecKillList() {
		return secKillDao.queryAll(0, 4);
	}

	@Override
	public SecKill getById(long secKillId) {
		return secKillDao.queryById(secKillId);
	}

	@Override
	public Exposer exportSecKillUrl(long secKillId) {
		SecKill secKill = secKillDao.queryById(secKillId);

		if (Objects.isNull(secKill)) {
			return new Exposer(false, secKillId);
		}

		Date startTime = secKill.getStartTime();
		Date endTime = secKill.getEndTime();
		Date nowTime = new Date();

		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, secKillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMD5(secKillId);

		return new Exposer(true, secKillId, md5);
	}

	@Override
	@Transactional
	public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
			throws SecKillException, RepeatKillException, SecKillCloseException {
		if (Objects.isNull(md5) || md5.equals(getMD5(secKillId))) {
			throw new SecKillException("seckill data rewrite");
		}

		try {
			Date nowTime = new Date();
			int updateCount = secKillDao.reduceNumber(secKillId, nowTime);

			if (updateCount <= 0) {
				throw new SecKillCloseException("SecKill is closed");
			} else {
				int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);

				if (insertCount <= 0) {
					throw new RepeatKillException("SecKill repeated");
				} else {
					SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId, userPhone);

					return new SecKillExecution(secKillId, SecKillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SecKillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecKillException("Seckill innser error: " + e.getMessage());
		}
	}

	private String getMD5(long secKillId) {
		String base = secKillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}
