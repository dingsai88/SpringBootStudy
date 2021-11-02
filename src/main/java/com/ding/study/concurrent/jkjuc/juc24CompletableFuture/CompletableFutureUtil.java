package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel 2021-11-1 0001.
 */
public class CompletableFutureUtil {
    public static void main(String[] args) throws Exception {
       List<CFReqBean> reqBeanList=new ArrayList<>();

/*            CompletableFuture<Void> future = CompletableFuture.allOf(reqBeanList.stream()
                .map(reqBean -> CompletableFuture.runAsync(getProductRunnable(fetchProductReq, p2puserBean, productVOList), threadPoolTaskExecutor))
                .toArray(CompletableFuture[]::new));

            future.get(30, TimeUnit.SECONDS);*/


/**


 // 需要返回的对象，InvestHeaderVo list 会并发
 InvestHeaderVo<InvestHeaderBeanVo> investHeaderBeanVo = new InvestHeaderVo();
 //1-4 去重 + 5全量
 List<CardOfHeaderPO> list = cardOfHeaderBeanMapper.selectListByFirstPage(new CardOfHeaderPO());

 List<CompletableFuture> completableFutureList = new ArrayList<>();


 //关怀区卡片
 for (CardOfHeaderPO cardHead : list) {
 //返回各个子项的线程对象
 MDCRunnable runnable = getInvestTab(user, investHeaderBeanVo, cardHead);
 if (runnable != null) {
 //非异常的才开线程
 completableFutureList.add(CompletableFuture.runAsync(runnable, threadPoolTaskExecutor));
 }
 }

 //获取并行结果
 CompletableFuture<Void> future = CompletableFuture.allOf( completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));

        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            log.error("异步查询信息失败", e);
        } catch (InterruptedException e) {
            log.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("调用服务超时", e);
        }
        log.info("最终返回数据集:{}", JsonUtils.beanToJson(investHeaderBeanVo));


 //创建 runnable
 private MDCRunnable getInvestTab(P2puserBean user, InvestHeaderVo<InvestHeaderBeanVo> result, CardOfHeaderPO cardOfHeaderPO) {
 return new MDCRunnable("加载 最爱|" + cardOfHeaderPO.getId()) {
@Override
public void doRun() {
cardHeadService.getFavorite(user, result, cardOfHeaderPO);
}
};

 */





        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 finished!");
            return "future1 finished!";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 finished!");
            return "future2 finished!";
        });
        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);

        try {
            System.out.println("main 1");
            System.out.println(" combindFuture.get();: " + combindFuture.get(100,TimeUnit.MILLISECONDS));
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());
        System.out.println("future1 get: " + future1.get() + " future2 get: " + future2.get());

    }



}
