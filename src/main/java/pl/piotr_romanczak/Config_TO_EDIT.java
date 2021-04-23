package pl.piotr_romanczak;

public class Config_TO_EDIT {
    private static final String WEATHER_API_KEY = "YOUR-OPEN-WEATHER-API-KEY";
    private static final String LANGUAGE = "pl";
    private static final String UNITS = "metric";

    public String getWeatherApiKey() {
        return WEATHER_API_KEY;
    }

    public String getLanguage() {
        return LANGUAGE;
    }

    public String getUnits() {
        return UNITS;
    }
}
