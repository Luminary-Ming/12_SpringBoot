package com.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 mybatis-plus 插件
 */
@Configuration
@MapperScan("com.demo.mapper")
public class MybatisPlusConfig {
    /**
     * 添加插件
     * @Bean 注解的作用是将方法返回的对象交给 Spring 管理
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 创建 mybatis-plus 拦截器对象
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        /*
            ！！！！！ 如果配置多个插件, 切记分页最后添加 ！！！！！
            在使用多个插件时，请将分页插件放到插件执行链的最后面，以避免 COUNT SQL 执行不准确的问题。
         */
        // 添加分页拦截器，并设置数据库类型为 MySQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 返回拦截对象
        return interceptor;
    }
}
/*
    在应用层面会造成线程阻塞的是悲观锁，而不会造成线程阻塞的是乐观锁

    1、悲观锁（高并发、数据竞争激烈的场景，如金融交易、库存管理等）
    悲观锁是一种基于悲观态度的数据并发控制机制，用于防止数据冲突。它采取预防性的措施，在修改数据之前将其锁定，
    并在操作完成后释放锁定，以确保数据的一致性和完整性。悲观锁通常用于并发环境下的数据库系统，是数据库本身实现锁机制的一种方式。

    在悲观锁的机制下，当一个使用者要修改某个数据时，首先会尝试获取该数据的锁。如果锁已经被其他使用者持有，
    则当前使用者会被阻塞，直到对应的锁被释放。这种悲观的态度认为数据冲突是不可避免的，因此在修改数据之前先锁定数据，以防止冲突的发生。

    在Java中，常见的悲观锁实现是使用synchronized关键字或ReentrantLock类。
    这些锁能够确保同一时刻只有一个线程可以访问被锁定的代码块或资源，其他线程必须等待锁释放后才能继续执行。

    2、乐观锁（读操作多、写操作少的场景，如一些阅读类应用、CMS系统等）
    乐观锁是一种基于版本控制的并发控制机制。在乐观锁的思想中，认为数据访问冲突的概率很低，
    因此不加锁直接进行操作，但在更新数据时会进行版本比对，以确保数据的一致性。

    乐观锁的原理主要基于版本号或时间戳来实现。在每次更新数据时，先获取当前数据的版本号或时间戳，然后在更新时比对版本号或时间戳是否一致，
    若一致则更新成功，否则表示数据已被其他线程修改，更新失败。

    在Java中，常见的乐观锁实现是使用Atomic类，例如AtomicInteger、AtomicLong等。这些类提供了原子操作，
    可以确保对共享资源的更新操作是原子性的，从而避免了锁的开销和线程等待，另外，CAS（Compare-And-Swap）是实现乐观锁的核心算法，
    它通过比较内存中的值是否和预期的值相等来判断是否存在冲突。如果存在，则返回失败；如果不存在，则执行更新操作。
    Java中提供了AtomicInteger、AtomicLong、AtomicReference等原子类来支持CAS操作。

    1、悲观锁
    优点：
      ● 数据一致性高：悲观锁认为冲突一定会发生，因此在数据处理前会先加锁，这样可以确保数据在任一时刻只被一个事务访问和修改，从而避免数据的不一致性和脏读。
      ● 简单易用：悲观锁的实现相对简单，只需要在操作数据前获取锁即可。
    缺点：
      ● 性能开销大：悲观锁在操作数据前需要获取锁，如果有大量的并发操作，可能会导致性能问题，因为其他事务需要等待锁释放。
      ● 容易造成死锁：如果多个事务相互等待对方释放锁，可能会导致死锁的发生，影响系统的稳定性和可用性。
      ● 可能导致资源浪费：如果获取锁后长时间不释放，可能会导致其他事务无法操作数据，从而造成资源浪费。

    2、乐观锁
    优点：
      ● 高并发高吞吐：乐观锁不会阻塞其他事务的读取操作，只在提交时检查数据是否被修改，因此可以提供更好的并发性能。
      ● 无锁操作：乐观锁不需要显式地获取和释放锁，减少了锁竞争和上下文切换的开销。
      ● 无死锁风险：由于乐观锁不会阻塞其他事务的访问，因此不会出现死锁的情况。
    缺点：
      ● 冲突处理复杂：由于乐观锁不会阻塞其他事务，因此在提交时需要检查数据是否被其他事务修改，如果发现冲突，需要回滚事务或重新尝试操作，这增加了冲突处理的复杂性。
      ● 数据一致性风险：乐观锁假设并发冲突较少，因此可能存在数据一致性的风险。如果多个事务同时对同一数据进行修改，可能会导致数据不一致的情况。
      ● 需要额外字段：为了实现乐观锁，通常需要在数据表中添加额外的版本号或时间戳字段，这增加了存储空间的需求。
      ● 处理不当造成死循环风险：在大多数业务中乐观锁更新失败都会进行自旋，如果没有控制好自旋退出逻辑可能会造成递归死循环问题。

 */
