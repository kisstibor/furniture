package ro.sapientia.furniture.service;

import java.util.Scanner;

public class AccountForSettingsService {
	
	private int customerNumber;
	private int pinNumber;
	
	Scanner input = new Scanner(System.in);
	
	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}
	
	public int getPinNumber() {
		return pinNumber;
	}

}
