# Pogodynka
Java desktop application which allows user to check weather. 
App have been made using:
- Java
- JavaFX
- SceneBuilder
- Open Weather Map API
- Maxmind GeoLite2 DB
- GSON library
- Maven

## Functionalities
- [x] Estimating user location by external IP address
- [x] Autosuggestion while entering city name
- [x] Displaying current weather data in two different cities
- [x] Displaying weather forecast for next 7 days

## How to run
 Internet connection is necesssary. You have to have Java SDK version 11+ installed.
 There are two ways to run this app. Application has been created using IntelliJ IDEA, so it     is recommended to use it for code compilation. 
 ###First way to run app:
 - download or clone whole repository
 - register to https://openweathermap.org/api and get your own API key
 - rename file Config_TO_EDIT to Config and enter your API key in the variable "WEATHER_API_KEY"
 - if needed, in the file pom.xml edit line 64 with your path to java.exe
 
###Second way to run app:
 - [download .jar file](https://drive.google.com/file/d/13GrymXB6E8dwMnASL3Ocq5FusAbNdWpi/view?usp=sharing) 
 - run i.e. in your cmd: java -jar /path/to/pogodynka.jar