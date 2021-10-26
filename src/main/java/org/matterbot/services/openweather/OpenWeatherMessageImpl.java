package org.matterbot.services.openweather;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

import static org.matterbot.services.Constants.NEWLINE;

@Data
@Builder
public class OpenWeatherMessageImpl implements MattermostMessage {
    private String caption;
    private String temp;
    private String tempMin;
    private String tempMax;
    private String pressure;
    private String humidity;
    private String wind;
    private String weather;
    private String icon;
    private final String ICON_BASE_URL = "http://openweathermap.org/img/w/"; //+ ICON_BASE_URL + icon + ".png" + NEWLINE

    @Override
    public String getMessage() {
        return caption + NEWLINE
                + "Temperature is: " + temp + " (min: " + tempMin + ", max: " + tempMax + ")" + NEWLINE
                + "Wind: " + wind + ", Humidity: " + humidity + ", Pressure: " + pressure + NEWLINE;
    }
}
