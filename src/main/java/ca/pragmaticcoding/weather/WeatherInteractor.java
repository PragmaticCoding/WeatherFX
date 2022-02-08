package ca.pragmaticcoding.weather;


import java.util.Arrays;


public class WeatherInteractor {

    private final WeatherModel viewModel;
    private final WeatherFetcher weatherfetcher = new WeatherFetcher();
    private WeatherData weatherData;

    public WeatherInteractor(WeatherModel viewModel) {
        this.viewModel = viewModel;
        viewModel.setCities(Arrays.asList("London", "Paris", "Nice", "Hong Kong", "New York", "Las Vegas"));
    }

    public void checkWeather() {
        weatherData = weatherfetcher.checkWeather(viewModel.getCity());
    }

    public void updateWeatherModel() {
        viewModel.setTemperature(weatherData.getTemperature());
        viewModel.setConditions(weatherData.getConditions());
        viewModel.setIcon(weatherData.getWeatherImage());
    }
}
