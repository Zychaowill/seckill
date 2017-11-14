package org.seckill.web;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.seckill.dto.SecKillExecution;
import org.seckill.dto.SecKillResult;
import org.seckill.entity.SecKill;
import org.seckill.enums.SecKillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>
 * Title: SeckillController
 * </p>
 * <p>
 * Description:
 * </p>
 * Created by jangz on 2017/10/18
 */
@Controller
@RequestMapping("/seckill/")
public class SeckillController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SecKillService secKillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<SecKill> list = secKillService.getSecKillList();
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("secKillId") Long secKillId, Model model) {
		if (Objects.isNull(secKillId)) {
			return "redirect:/seckill/list";
		}

		SecKill secKill = secKillService.getById(secKillId);

		if (Objects.isNull(secKill)) {
			return "redirect:/seckill/list";
		}

		model.addAttribute("secKill", secKill);
		return "detail";
	}

	@RequestMapping(value = "/{secKillId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public SecKillResult<SecKillExecution> execute(@PathVariable("secKillId") Long secKillId,
			@PathVariable("md5") String md5, Long userPhone) {
		if (Objects.isNull(userPhone)) {
			return new SecKillResult<SecKillExecution>(false, "No register");
		}

		SecKillResult<SecKillExecution> result = null;

		try {
			SecKillExecution secKillExecution = secKillService.executeSecKill(secKillId, userPhone, md5);
			result = new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch (RepeatKillException ex) {
			SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.REPEAT);
			result = new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch (SecKillCloseException ex) {
			SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.END);
			result = new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.INNER_ERROR);
			result = new SecKillResult<SecKillExecution>(true, secKillExecution);
		}

		return result;
	}
	
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SecKillResult<Long> time() {
		Date now = new Date();
		return new SecKillResult<Long>(true, now.getTime());
	}
}
