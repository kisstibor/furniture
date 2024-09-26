package ro.sapientia.furniture.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class OptionMenuForSettingsService extends AccountForSettingsService {
	
	Scanner menuInput = new Scanner(System.in);
	
HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
	
	public void getLogin() throws IOException {
		int x = 1;
		do {
			try {
				data.put(952141, 191904);
				data.put(989947, 71976);
				
				System.out.println("Welcome to the Furniture Service!");
				System.out.println("Enter your customer number");
				setCustomerNumber(menuInput.nextInt());
				
				System.out.println("Enter your PIN number");
				setPinNumber(menuInput.nextInt());
			} catch (Exception e) {
				System.out.println("\n" + "Invalid Character(s). Only Numbers." + "\n");
				x = 2;
			}
			/*
			 * for(Map.Entry<Integer,Integer> it: data.entrySet()){
			 * if(it.getkey()==getCustomerNumber() && it.getValue()==getPinNumber){
			 * getAccounttype();}} 
			 * */
			int cn = getCustomerNumber();
			int pn = getPinNumber();
			if (data.containsKey(cn) && data.get(cn) == pn) {
				//getAccountType();
				getFurnitureType();
			} else
				System.out.println("\n" + "Wrong Customer Number or Pin Number" + "\n");
			} while (x == 1); 
		}
	public void getFurnitureType() {
		System.out.println("Select the furniture type: ");
		System.out.println("Type 1 - kitchen furniture");
		System.out.println("Type 2 - living room furniture");
		System.out.println("Type 3 - bedroom furniture");
		System.out.println("Type 4 - bathroom furniture");
		System.out.println("Type 5 - garden furniture");
		System.out.println("Type 6 - office furniture");
		System.out.println("Type 7 - laboratory furniture");
		System.out.println("Type 8 - workshop furniture");
		System.out.println("Type 9 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			getKitchenFurnitureType();
			break;
		
		case 2:
			getLivingRoomFurnitureType();
			break;
			
		case 3:
			getBedroomFurnitureType();
			break;
			
		case 4:
			getBathroomFurnitureType();
			break;
			
		case 5:
			getGardenFurnitureType();
			break;
			
		case 6:
			getOfficeFurnitureType();
			break;
			
		case 7:
			getLaboratoryFurnitureType();
			break;
			
		case 8:
			getWorkshopFurnitureType();
			break;
			
		case 9:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getKitchenFurnitureType() {
		System.out.println("Select the kitchen furniture type: ");
		System.out.println("Type 1 - small kitchen furniture");
		System.out.println("Type 2 - wood kitchen furniture");
		System.out.println("Type 3 - modern kitchen furniture");
		System.out.println("Type 4 - simple kitchen furniture");
		System.out.println("Type 5 - design kitchen furniture");
		System.out.println("Type 6 - modular kitchen furniture");
		System.out.println("Type 7 - pvc kitchen furniture");
		System.out.println("Type 8 - interior kitchen furniture");
		System.out.println("Type 9 - grey kitchen furniture");
		System.out.println("Type 10 - new kitchen furniture");
		System.out.println("Type 11 - cabinet kitchen furniture");
		System.out.println("Type 12 - luxury kitchen furniture");
		System.out.println("Type 13 - space saving kitchen furniture");
		System.out.println("Type 14 - stainless steel kitchen furniture");
		System.out.println("Type 15 - cupboard kitchen furniture");
		System.out.println("Type 16 - plywood kitchen furniture");
		System.out.println("Type 17 - ikea kitchen furniture");
		System.out.println("Type 18 - colour kitchen furniture");
		System.out.println("Type 19 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getLivingRoomFurnitureType() {
		System.out.println("Select the living room furniture type: ");
		System.out.println("Type 1 - modern living room furniture");
		System.out.println("Type 2 - luxury living room furniture");
		System.out.println("Type 3 - wood living room furniture");
		System.out.println("Type 4 - simple living room furniture");
		System.out.println("Type 5 - gray living room furniture");
		System.out.println("Type 6 - leather living room furniture");
		System.out.println("Type 7 - elegant living room furniture");
		System.out.println("Type 8 - contemporany living room furniture");
		System.out.println("Type 9 - brown living room furniture");
		System.out.println("Type 10 - traditional living room furniture");
		System.out.println("Type 11 - sectional living room furniture");
		System.out.println("Type 12 - small living room furniture");
		System.out.println("Type 13 - design living room furniture");
		System.out.println("Type 14 - rustic living room furniture");
		System.out.println("Type 15 - classic living room furniture");
		System.out.println("Type 16 - sofa living room furniture");
		System.out.println("Type 17 - farmhouse living room furniture");
		System.out.println("Type 18 - minimalist living room furniture");
		System.out.println("Type 19 - cheap living room furniture");
		System.out.println("Type 20 - blue living room furniture");
		System.out.println("Type 21 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 20:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 21:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getBedroomFurnitureType() {
		System.out.println("Select the bedroom furniture type: ");
		System.out.println("Type 1 - modern bedroom furniture");
		System.out.println("Type 2 - wood bedroom furniture");
		System.out.println("Type 3 - simple bedroom furniture");
		System.out.println("Type 4 - luxury bedroom furniture");
		System.out.println("Type 5 - design bedroom furniture");
		System.out.println("Type 6 - grey bedroom furniture");
		System.out.println("Type 7 - contemporany bedroom furniture");
		System.out.println("Type 8 - small bedroom furniture");
		System.out.println("Type 9 - beautiful bedroom furniture");
		System.out.println("Type 10 - wardrobe bedroom furniture");
		System.out.println("Type 11 - royal bedroom furniture");
		System.out.println("Type 12 - unique bedroom furniture");
		System.out.println("Type 13 - elegant bedroom furniture");
		System.out.println("Type 14 - stylish bedroom furniture");
		System.out.println("Type 15 - classic bedroom furniture");
		System.out.println("Type 16 - new bedroom furniture");
		System.out.println("Type 17 - solid wood bedroom furniture");
		System.out.println("Type 18 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getBathroomFurnitureType() {
		System.out.println("Select the bathroom furniture type: ");
		System.out.println("Type 1 - modern bathroom furniture");
		System.out.println("Type 2 - wood bathroom furniture");
		System.out.println("Type 3 - freestanding bathroom furniture");
		System.out.println("Type 4 - luxury bathroom furniture");
		System.out.println("Type 5 - grey bathroom furniture");
		System.out.println("Type 6 - small bathroom furniture");
		System.out.println("Type 7 - wall hung bathroom furniture");
		System.out.println("Type 8 - ikea bathroom furniture");
		System.out.println("Type 9 - traditional bathroom furniture");
		System.out.println("Type 10 - fitted bathroom furniture");
		System.out.println("Type 11 - vanity bathroom furniture");
		System.out.println("Type 12 - walnut bathroom furniture");
		System.out.println("Type 13 - gloss bathroom furniture");
		System.out.println("Type 14 - storage bathroom furniture");
		System.out.println("Type 15 - contemporany bathroom furniture");
		System.out.println("Type 16 - rustic bathroom furniture");
		System.out.println("Type 17 - metal bathroom furniture");
		System.out.println("Type 18 - oak bathroom furniture");
		System.out.println("Type 19 - mirror bathroom furniture");
		System.out.println("Type 20 - duravit bathroom furniture");
		System.out.println("Type 21 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 20:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 21:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getGardenFurnitureType() {
		System.out.println("Select the garden furniture type: ");
		System.out.println("Type 1 - wooden garden furniture");
		System.out.println("Type 2 - rattan garden furniture");
		System.out.println("Type 3 - metal garden furniture");
		System.out.println("Type 4 - modern garden furniture");
		System.out.println("Type 5 - outdoor garden furniture");
		System.out.println("Type 6 - corner garden furniture");
		System.out.println("Type 7 - luxury garden furniture");
		System.out.println("Type 8 - grey garden furniture");
		System.out.println("Type 9 - plastic garden furniture");
		System.out.println("Type 10 - weatherproof garden furniture");
		System.out.println("Type 11 - wicker garden furniture");
		System.out.println("Type 12 - cheap garden furniture");
		System.out.println("Type 13 - contemporany garden furniture");
		System.out.println("Type 14 - unusual garden furniture");
		System.out.println("Type 15 - sofa garden furniture");
		System.out.println("Type 16 - patio garden furniture");
		System.out.println("Type 17 - aluminium garden furniture");
		System.out.println("Type 18 - 8 seater garden furniture");
		System.out.println("Type 19 - round garden furniture");
		System.out.println("Type 20 - dining garden furniture");
		System.out.println("Type 21 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 20:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 21:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getOfficeFurnitureType() {
		System.out.println("Select the office furniture type: ");
		System.out.println("Type 1 - modern office furniture");
		System.out.println("Type 2 - executive office furniture");
		System.out.println("Type 3 - wood office furniture");
		System.out.println("Type 4 - workstation office furniture");
		System.out.println("Type 5 - table office furniture");
		System.out.println("Type 6 - luxury office furniture");
		System.out.println("Type 7 - desk office furniture");
		System.out.println("Type 8 - design office furniture");
		System.out.println("Type 9 - contemporany office furniture");
		System.out.println("Type 10 - modular office furniture");
		System.out.println("Type 11 - reception office furniture");
		System.out.println("Type 12 - sofa office furniture");
		System.out.println("Type 13 - cabinet office furniture");
		System.out.println("Type 14 - home office furniture");
		System.out.println("Type 15 - chair office furniture");
		System.out.println("Type 16 - small office furniture");
		System.out.println("Type 17 - interior office furniture");
		System.out.println("Type 18 - industrial office furniture");
		System.out.println("Type 19 - cubicle office furniture");
		System.out.println("Type 20 - classic office furniture");
		System.out.println("Type 21 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 20:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 21:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getLaboratoryFurnitureType() {
		System.out.println("Select the laboratory furniture type: ");
		System.out.println("Type 1 - waldner laboratory furniture");
		System.out.println("Type 2 - steel laboratory furniture");
		System.out.println("Type 3 - modular laboratory furniture");
		System.out.println("Type 4 - design laboratory furniture");
		System.out.println("Type 5 - modern laboratory furniture");
		System.out.println("Type 6 - wood laboratory furniture");
		System.out.println("Type 7 - used laboratory furniture");
		System.out.println("Type 8 - cabinet laboratory furniture");
		System.out.println("Type 9 - manufacturers laboratory furniture");
		System.out.println("Type 10 - school laboratory furniture");
		System.out.println("Type 11 - cad laboratory furniture");
		System.out.println("Type 12 - hospital laboratory furniture");
		System.out.println("Type 13 - blue laboratory furniture");
		System.out.println("Type 14 - bench laboratory furniture");
		System.out.println("Type 15 - classroom laboratory furniture");
		System.out.println("Type 16 - chemistry lab laboratory furniture");
		System.out.println("Type 17 - engineering laboratory furniture");
		System.out.println("Type 18 - science lab laboratory furniture");
		System.out.println("Type 19 - fume laboratory furniture");
		System.out.println("Type 20 - table laboratory furniture");
		System.out.println("Type 21 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 19:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 20:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 21:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}
	
	public void getWorkshopFurnitureType() {
		System.out.println("Select the workshop furniture type: ");
		System.out.println("Type 1 - wood workshop furniture");
		System.out.println("Type 2 - architecture workshop furniture");
		System.out.println("Type 3 - garage workshop furniture");
		System.out.println("Type 4 - drawing workshop furniture");
		System.out.println("Type 5 - diy workshop furniture");
		System.out.println("Type 6 - carpentry workshop furniture");
		System.out.println("Type 7 - craft workshop furniture");
		System.out.println("Type 8 - dura workshop furniture");
		System.out.println("Type 9 - shop workshop furniture");
		System.out.println("Type 10 - upcycling workshop furniture");
		System.out.println("Type 11 - factory workshop furniture");
		System.out.println("Type 12 - omega workshop furniture");
		System.out.println("Type 13 - upholstery workshop furniture");
		System.out.println("Type 14 - small workshop furniture");
		System.out.println("Type 15 - modular workshop furniture");
		System.out.println("Type 16 - sewa workshop furniture");
		System.out.println("Type 17 - jakarta workshop furniture");
		System.out.println("Type 18 - Exit");
		
		int selection = menuInput.nextInt();
		
		switch (selection) {
		case 1:
			//getChecking();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
		
		case 2:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 3:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 4:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 5:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 6:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 7:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 8:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 9:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 10:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 11:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 12:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 13:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 14:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 15:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 16:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 17:
			//getSaving();
			System.out.println("Your selection is. \n" + selection);
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		case 18:
			System.out.println("Thank You for using our Furniture Service, bye. \n");
			break;
			
		default:
			System.out.println("\n" + "Invalid Choice" + "\n");
			getFurnitureType();
		}
	}

}
