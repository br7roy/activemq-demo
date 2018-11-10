 /*
  * Package com.rust.activemq
  * FileName: Singleton
  * Author:   Rust
  * Date:     2018/6/11 21:48
  */
 package com.rust.activemq;

 /**
  * FileName:    Singleton
  * Author:      Rust
  * Date:        2018/6/11
  * Description:
  */
 public class Singleton {












	 private static class SingleTonHolder {
		 private static final Singleton singleton = new Singleton();

	 }

	 public static Singleton getinstance() {
		 return SingleTonHolder.singleton;
	 }
 }
