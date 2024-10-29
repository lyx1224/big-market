package cn.lyx.test.domain;

import cn.lyx.domain.strategy.model.entity.RaffleAwardEntity;
import cn.lyx.domain.strategy.model.entity.RaffleFactorEntity;
import cn.lyx.domain.strategy.model.entity.RuleActionEntity;
import cn.lyx.domain.strategy.service.IRaffleStrategy;
import cn.lyx.domain.strategy.service.armory.IStrategyArmory;
import cn.lyx.domain.strategy.service.rule.impl.RuleLockLogicFilter;
import cn.lyx.domain.strategy.service.rule.impl.RuleWeightLogicFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * @author lyx
 * @description
 * @since 2024/10/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {
    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IRaffleStrategy raffleStrategy;
    @Resource
    private RuleWeightLogicFilter ruleWeightLogicFilter;
    @Resource
    private RuleLockLogicFilter ruleLockLogicFilter;

    @Before
    public void setUp() {
        // 策略装配 100001、100002、100003
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100001L));
        //log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100002L));
        log.info("测试结果：{}", strategyArmory.assembleLotteryStrategy(100003L));


        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 100000L);
        ReflectionTestUtils.setField(ruleLockLogicFilter, "userRaffleCount", 0L);
    }

    @Test
    public void test_performRaffle(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("lyx")
                .strategyId(100001L)
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("请求结果：{}", JSON.toJSONString(raffleAwardEntity));
    }
    @Test
    public void test_performRaffle_blacklist(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("user001")
                .strategyId(100001L)
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("请求结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_raffle_center_rule_lock(){
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("user001")
                .strategyId(100003L)
                .build();

        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }
}



