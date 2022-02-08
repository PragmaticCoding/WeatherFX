package ca.pragmaticcoding.weather;

import javafx.concurrent.Task;
import javafx.scene.layout.Region;

public class WeatherController {

    private final WeatherInteractor interactor;
    private final WeatherViewBuilder viewBuilder;

    public WeatherController() {
        WeatherModel viewModel = new WeatherModel();
        interactor = new WeatherInteractor(viewModel);
        viewBuilder = new WeatherViewBuilder(viewModel, this::fetchWeather);
    }

    private void fetchWeather(Runnable postFetchGuiStuff) {
        Task<Void> fetchTask = new Task<>() {
            @Override
            protected Void call() {
                interactor.checkWeather();
                return null;
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            interactor.updateWeatherModel();
            postFetchGuiStuff.run();
        });
        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
