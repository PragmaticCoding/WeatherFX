package ca.pragmaticcoding.weather;

import ca.pragmaticcoding.widgetfx.TextWidgets;
import ca.pragmaticcoding.widgetfx.TwoColumnGridPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.Objects;
import java.util.function.Consumer;

public class WeatherViewBuilder implements Builder<Region> {

    private final WeatherModel viewModel;
    private final Consumer<Runnable> weatherFetcher;

    public WeatherViewBuilder(WeatherModel viewModel, Consumer<Runnable> weatherFetcher) {
        this.viewModel = viewModel;
        this.weatherFetcher = weatherFetcher;
    }

    @Override
    public Region build() {
        BorderPane results = new BorderPane();
        results.setCenter(setUpCentre());
        results.setBottom(setUpBottom(weatherFetcher));
        results.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/default.css")).toExternalForm());
        results.getStyleClass().add("weather-box");
        return results;
    }

    private Node setUpCentre() {
        ImageView iconImageView = new ImageView();
        iconImageView.imageProperty().bind(viewModel.iconProperty());
        iconImageView.setFitWidth(200);
        TwoColumnGridPane gridPane = new TwoColumnGridPane();
        gridPane.addTextRow("Temperature:", viewModel.temperatureProperty());
        gridPane.addTextRow("Conditions:", viewModel.conditionsProperty());
        gridPane.setMinHeight(160);
        gridPane.setMinWidth(200);
        VBox vBox = new VBox(5, TextWidgets.boundStyledText(viewModel.cityProperty(), "heading-text"), gridPane);
        HBox hBox = new HBox(10, iconImageView, vBox);

        vBox.getStyleClass().add("info-box");
        vBox.setPadding(new Insets(6));
        return hBox;
    }

    private Node setUpBottom(Consumer<Runnable> fetchWeather) {
        Button button = new Button("Get Weather");
        button.setOnAction(evt -> {
            button.setDisable(true);
            fetchWeather.accept(() -> {
                button.setDisable(false);
            });
        });
        ComboBox<String> cityCB = new ComboBox<>();
        cityCB.getItems().setAll(viewModel.getCities());
        viewModel.cityProperty().bind(cityCB.valueProperty());
        ToggleButton unitsToggle = new ToggleButton();

        return new HBox(10, cityCB, button);
    }
}
