package com.qf.datatool;

import com.qf.datatool.client.PropertyPublishStatusClient;
import com.qf.datatool.configuration.CfgProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DatatoolApplication.class)
public class DatatoolApplicationTests {

	private static final Log log = LogFactory.getLog(DatatoolApplicationTests.class);

	@Autowired
	private CfgProperties cfgProperties;
	@Autowired
	PropertyPublishStatusClient propertyPublishStatusClient;

	@Test
	public void test1() throws Exception {
		List<String> strings = propertyPublishStatusClient.listProPublishProperty();
		if(!CollectionUtils.isEmpty(strings)){
			long start = System.currentTimeMillis();
			for(int i = 0;i<strings.size();i++){
				System.out.println(strings.get(i));
			}
			long end = System.currentTimeMillis();
			System.out.println("循环打印共耗时"+(end-start));
		}
	}

	/*@Test
	public void executorTest1() throws Exception {
		List<String> strings = propertyPublishStatusClient.listProPublishProperty();
		List<String> concurrentStrings = Collections.synchronizedList(strings);
		//多线程打印
		ExecutorService executorService =  Executors.newWorkStealingPool();
		MyTask myTask1 = new MyTask(0, 500, concurrentStrings);
		MyTask myTask2 = new MyTask(500, 1000, concurrentStrings);
		MyTask myTask3 = new MyTask(1000, 1500, concurrentStrings);
		MyTask myTask4 = new MyTask(1500, 2000, concurrentStrings);
		MyTask myTask5 = new MyTask(2000, 2500, concurrentStrings);
		MyTask myTask6 = new MyTask(2500, 3000, concurrentStrings);
		Future<Integer> f1 = executorService.submit(myTask1);
		Future<Integer> f2 = executorService.submit(myTask2);
		Future<Integer> f3 = executorService.submit(myTask3);
		Future<Integer> f4 = executorService.submit(myTask4);
		Future<Integer> f5 = executorService.submit(myTask5);
		Future<Integer> f6 = executorService.submit(myTask6);
		long start = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		f5.get();
		f6.get();
		long end = System.currentTimeMillis();
		System.out.println("多线程循环打印共耗时"+(end-start));
	}

	*//**
	 * 打印任务
	 *//*
	static class MyTask implements Callable<Integer>{
		int start;
		int end;
		List<String> strings;
		MyTask(int start,int end,List<String> strings){
			this.start = start;
			this.end = end;
			this.strings = strings;
		}

		@Override
		public Integer call() throws Exception {
			for(int i = start;i <end;i++){
				System.out.println(strings.get(i));
			}
			return 1;
		}
	}*/

	@Test
	public void test2() throws Exception {
		//得到所有的publishPropertyUuid
		List<String> publishPropertyUuids = propertyPublishStatusClient.listProPublishProperty();
		if(!CollectionUtils.isEmpty(publishPropertyUuids)){
			for(int i = 0;i<publishPropertyUuids.size();i++){
				propertyPublishStatusClient.offPublishStatus("yifangwangyibinceshi_companycd12312e3",publishPropertyUuids.get(i));
			}
		}
		//propertyPublishStatusClient.offPublishStatus("qingdaokeweibudongch_company8d9d1aae2","04914b9a-1f07-4cd0-9ae8-0ae778aaf84e");
	}

}
