#include <Wire.h> //Biblioteca para usar I2C  
#include <LiquidCrystal_I2C.h> //Biblioteca para usar display LCD do tipo I2C 
#include <DHT.h> //Biblioteca para usar o sensor DHT11

#define DHTPIN A1 
#define DHTTYPE DHT11


LiquidCrystal_I2C lcd(0x27, 16, 2); //inicializa objeto lcd para classe LiquidCrystal_I2C com endereço I2C de 0x27 e display LCD tipo 16x2


DHT dht(DHTPIN, DHTTYPE); //inicializa objeto dht para classe DHT com pino DHT com STM32 e do tipo DHT como DHT11


void setup() 

{ 

  // inicializa o LCD 
  
  lcd.init();
  
  lcd.backlight(); // Acende a luz negra e imprime uma mensagem de boas-vindas. 

  delay(1000);

  dht.begin(); //Começa a receber os valores de temperatura e umidade.                        

  lcd.setCursor(0,0); 

  lcd.print("GRUPO 7 MISA"); 

  lcd.setCursor(0,1); 

  lcd.print("DHT11 com STM32"); 

  delay(6000); 

  lcd.clear(); 

}


void loop() 

{ 

  float h = dht.readHumidity(); // Obtém o valor de Humidade 

  float t = dht.readTemperature(); // Obtém o valor da temperatura 

  lcd.setCursor(0,0); 

  lcd.print("Temp: "); 

  lcd.print(t); // Imprime o valor da temperatura em ºC

  lcd.print(" C"); 

  lcd.setCursor(0,1); 

  lcd.print("Humid: "); 

  lcd.print(h);  // Imprime o valor da Humidade em %

  lcd.print(" %"); 

}