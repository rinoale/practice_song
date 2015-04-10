package com.syaku.bbs.service;

public class ServiceImpl implements Service {

 @Override
 public void serviceMethod() {
  System.out.println("serviceMethod()호출!!");
 }
 @Override
 public void serviceMethod(int x)
 {
	 System.out.println(x+"번 글을 열람하였습니다.");
 }

}
