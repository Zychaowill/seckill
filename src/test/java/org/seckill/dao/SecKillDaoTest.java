//package org.seckill.dao;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.seckill.entity.SecKill;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/spring-dao.xml")
//public class SecKillDaoTest {
//
//	@Resource
//	private SecKillDao secKillDao;
//
//	@Test
//	public void testReduceNumber() throws Exception {
//		Date killTime = new Date();
//		int result = secKillDao.reduceNumber(1000L, killTime);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testQueryById() throws Exception {
//		long id = 1000;
//		SecKill secKill = secKillDao.queryById(id);
//		System.out.println(secKill.getName());
//	}
//
//	@Test
//	public void testQueryAll() throws Exception {
//		List<SecKill> secKillList = secKillDao.queryAll(0, 1000);
//
//		secKillList.stream().forEach(row -> System.out.println(row.toString()));
//	}
//}
