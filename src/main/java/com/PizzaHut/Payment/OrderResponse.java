package com.PizzaHut.Payment;

public  class OrderResponse {
    private String orderId;


    public OrderResponse(String orderId) {
      this.orderId = orderId;
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
  }