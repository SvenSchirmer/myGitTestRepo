package com.demoaut.newtours;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class First_TestCase {
	
	//Locators within the Login-page
    private By input_UserName = By.name("userName");
    private By input_pwd = By.name("password");
    private By button_login = By.name("login");
    private By link_signoff = By.linkText("SIGN-OFF");
    
    //Locators within the Flight-Finder-Page
    private By dropdown_passengers = By.name("passCount"); // Anzahl Passagiere
    private By dropdown_depFrom = By.name("fromPort"); // Abflugsort
    private By dropdown_fromMonth = By.name("fromMonth"); // Abflugdatum: Monat
    private By dropdown_fromDay = By.name("fromDay"); // // Abflugdatum: Tag
    private By dropdown_toPort = By.name("toPort"); // Zielort
    private By dropdown_toMonth = By.name("toMonth"); // Rückflug: Monat
    private By dropdown_toDay = By.name("toDay"); // Rückflug: Tag
    
    private By radio_servClass = By.name("servClass"); // Klassenauswahl, mögliche Werte: Coach, Business, First
    
    private By dropdown_airline = By.name("airline"); // Auswahl Airline   
    private By button_findFlights = By.name("findFlights"); // Button "Continue"
    
    //private By txt_signOn = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font");
    

    @Test (groups = "simple")
    public void successfulLogin() {
    	
    	WebDriver wd = new FirefoxDriver(); 
        
    	// Seite öffnen
    	wd.get("http://newtours.demoaut.com/mercurysignon.php");

    	// Logindaten eingeben und "Login" klicken
    	
    	//System.out.println(wd.findElement(txt_signOn).getText());
    	
        wd.findElement(input_UserName).sendKeys("tutorial");
        wd.findElement(input_pwd).sendKeys("tutorial");
        wd.findElement(button_login).click();
                
        // Prüfen, ob der Login erfolgreich war - über den Titel der Seite
        Assert.assertEquals(wd.getTitle(), "Find a Flight: Mercury Tours:");
        
        // Ausloggen
        wd.findElement(link_signoff).click();
        
        // Browser schließen
        wd.quit();
    }
    
    @Test (groups = "complex")
    public void loginAndSelectFlight () {
    	WebDriver wd = new FirefoxDriver(); 
        		
    	// Seite öffnen
    	wd.get("http://newtours.demoaut.com/mercurysignon.php");

    	//=== Login ===
    	// Logindaten eingeben und "Login" klicken
        wd.findElement(input_UserName).sendKeys("tutorial");
        wd.findElement(input_pwd).sendKeys("tutorial");
        wd.findElement(button_login).click();
                
        // Prüfen, ob der Login erfolgreich war - über den Titel der Seite
        Assert.assertEquals(wd.getTitle(), "Find a Flight: Mercury Tours:");
        
        
      //=== Flug auswählen ===
      /*
       * 2 Personen
       * von Portland nach Sydney
       * Abflug: 20. Februar
       * Rückflung: 3. März
       */
        
       new Select(wd.findElement(dropdown_passengers)).selectByValue("2");	
       new Select(wd.findElement(dropdown_depFrom)).selectByValue("Portland");
       new Select(wd.findElement(dropdown_fromMonth)).selectByVisibleText("February");
       new Select(wd.findElement(dropdown_fromDay)).selectByValue("20");
       new Select(wd.findElement(dropdown_toPort)).selectByValue("Sydney");
       new Select(wd.findElement(dropdown_toMonth)).selectByVisibleText("March");
       new Select(wd.findElement(dropdown_toDay)).selectByValue("3");
       
       List<WebElement> radioButton =  wd.findElements(radio_servClass);
       String sValue;
       
       for(int i=0; i < radioButton.size() ; i++ ){
    	   sValue = radioButton.get(i).getAttribute("value");
    	   //System.out.println("Value an Stelle "+i+": ["+sValue+"]");

    	   if (sValue.equalsIgnoreCase("First")){
    		   radioButton.get(i).click();
    		   // This will take the execution out of for loop
    		   break;
    	   }
       }
    	  
       // Flüge suchen
       wd.findElement(button_findFlights).click();  
       
       // Ausloggen
       wd.findElement(link_signoff).click();
       
       // Browser schließen
       wd.quit();
       
    }
   
}