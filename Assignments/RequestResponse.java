//@author Connor Murray  github.com/HackdaNorth
package com.sliverstyle.SliverStyleWebApp.DTO;

import java.util.List;

import com.sliverstyle.SliverStyleWebApp.DomainObjects.Product;

public class RequestResponse {

	private boolean success = true; // successfully created account or logged in

	private String returnMessageToUser;
	private String htmlPage;
	private List<Product> listProducts;

	public RequestResponse(boolean condition, String returnMessage, String url) {
		this.success = condition;
		this.returnMessageToUser = returnMessage;
		this.htmlPage = url;
	}
	public RequestResponse(boolean condition, String url,  List<Product> listProducts ) {
		this.success = condition;
		this.htmlPage = url;
		this.listProducts = listProducts;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getHtmlPage() {
		return htmlPage;
	}
	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	public String getReturnMessageToUser() {
		return returnMessageToUser;
	}
	public void setReturnMessageToUser(String returnMessageToUser) {
		this.returnMessageToUser = returnMessageToUser;
	}
	public List<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}

}

