package com.ding.study.spring.datarest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * I.隐藏类里全部接口、方法、字段：
 * 1.隐藏类:@RepositoryRestResource(path = "bank",exported = false):exported = false 整个类都不能访问了。
 * 2.隐藏方法:@RestResource(exported = false)
 * 3.隐藏字段:@JsonIgnore
 * <p>
 * I.隐藏-默认的删除方法
 *
 * @author daniel 2021-2-19 0019.
 * @Override
 * @RestResource(exported = false)
 * void delete(Long id); *
 * @Override
 * @RestResource(exported = false)
 * void delete(Person entity);
 */
@RepositoryRestResource(path = "bank")
public interface BankService extends JpaRepository<Bank, Long> {
    /**
     * http://127.0.0.1:8080/bank/search/findByBankCode?bankCode=PAB&sort=createTime,desc
     *
     * @param key
     * @return
     */
    Bank findByBankCode(@Param("bankCode") String key);

    @Override
    @RestResource(exported = false)
    void delete(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Bank entity);
}
