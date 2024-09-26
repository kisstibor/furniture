package ro.sapientia.furniture.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestLoginService {
	@Test
	public void getterAndSetterIndeedWork() {
		AccountForSettingsService testedBean = new AccountForSettingsService();
	    testedBean.setCustomerNumber(952141);
	    assertEquals(952141, testedBean.getCustomerNumber());
	}
	@Test
	public void getterAndSetterIndeedWork2() {
		AccountForSettingsService testedBean = new AccountForSettingsService();
	    testedBean.setPinNumber(71976);
	    assertEquals(71976, testedBean.getPinNumber());
	}
	@Test
	public void getterAndSetterIndeedWork3() {
		AccountForSettingsService testedBean = new AccountForSettingsService();
	    testedBean.setPinNumber(191904);
	    assertEquals(191904, testedBean.getPinNumber());
	}
	@Test
	public void getterAndSetterIndeedWork4() {
		AccountForSettingsService testedBean = new AccountForSettingsService();
	    testedBean.setPinNumber(989947);
	    assertEquals(989947, testedBean.getPinNumber());
	}
		
}
