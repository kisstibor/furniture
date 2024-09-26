package ro.sapientia.furniture.service;

import java.io.IOException;

public class SettingsService extends OptionMenuForSettingsService{
	public static void main(String[] args) throws IOException {
		OptionMenuForSettingsService optionMenuForSettingsService = new OptionMenuForSettingsService();
		
		optionMenuForSettingsService.getLogin();
		
		System.out.println("get actual settings");
		System.out.println("modify settings");
		}

}
