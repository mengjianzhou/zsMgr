package com.robert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * JDBC锁互斥
 */
public class JDBCTest {
	
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/test";
	private static String username = "root";
	private static String password = "root";
	static Connection connection;
	static int CONCURRENT_NUM = 70;
	static CountDownLatch cdl= new CountDownLatch(CONCURRENT_NUM);
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,username,password);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ExecutorService executor = Executors.newFixedThreadPool(4);
		long start = System.nanoTime();	
		for(int i=0;i<CONCURRENT_NUM;i++){
			executor.execute(new Runnable(){
				public void run(){					
					try {
						Statement st = connection.createStatement();
//						System.out.println("Thread 1 before"+ Calendar.getInstance().getTime());
						st.executeQuery("select * from tb_crm_reward_rule where id=63 for update");
						try {
							TimeUnit.MILLISECONDS.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						connection.commit();
						cdl.countDown();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		try {
			cdl.await();
			System.out.println(System.nanoTime()-start);
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//			new Thread(new Runnable(){
//				public void run(){					
//					try {
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
//						connection.setAutoCommit(false);
//						Statement st = connection.createStatement();
//						long start = System.nanoTime();
////						System.out.println("Thread 1 before"+ Calendar.getInstance().getTime());
//						st.executeQuery("select * from tb_crm_reward_rule where id=63 for update");
//						System.out.println(System.nanoTime()-start);
////						TimeUnit.SECONDS.sleep(5);
//						connection.commit();
//						System.out.println("Thread 1 after "+Calendar.getInstance().getTime());
//						
//					} catch (ClassNotFoundException e) {
//						e.printStackTrace();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
//			
//			new Thread(new Runnable(){
//				public void run(){					
//					try {
//						Class.forName("com.mysql.jdbc.Driver");
//						Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
//						connection.setAutoCommit(false);
//						Statement st = connection.createStatement();
//						System.out.println("Thread 2 before"+Calendar.getInstance().getTime());
//						st.executeQuery("select * from tb_crm_reward_rule where id=63 for update");
//						TimeUnit.SECONDS.sleep(10);
//						connection.commit();
//						System.out.println("Thread 2 after"+Calendar.getInstance().getTime());
//					} catch (ClassNotFoundException e) {
//						e.printStackTrace();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
			
	}
}

















