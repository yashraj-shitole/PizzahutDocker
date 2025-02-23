package com.PizzaHut.Payment;
import org.json.JSONObject;

public class OrderRequest {
	
	private int amount;
	private String currency = "INR";
	
    public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}



    public JSONObject toJsonObject() {
      JSONObject options = new JSONObject();
      options.put("amount", amount);
      options.put("currency", currency);
      return options;
    }
  }