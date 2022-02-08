package ca.pragmaticcoding.weather;

import javafx.scene.image.Image;

public class WeatherData {

    private String conditions = "";
    private String temperature = "";
    private Image weatherImage;


    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }


    public Image getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(Image weatherImage) {
        this.weatherImage = weatherImage;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
