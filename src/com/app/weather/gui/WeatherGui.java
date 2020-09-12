package com.app.weather.gui;

import com.app.weather.WeatherForecast;
import com.app.weather.weatherApi.ForecastObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * The Singleton class that build GUI and did changes in the fields by user's actions.
 *
 */
public class WeatherGui {
    private static WeatherGui gui;

    private WeatherForecast forecast;
    private JFrame frame = new JFrame("Current weather.");
    private JPanel background = new JPanel();
    private JPanel bottomInfo = new JPanel();
    private JLabel info = new JLabel();

    private JPanel topSearchField = new JPanel();
    private JLabel text = new JLabel("Enter a city: ");
    private JTextField searchField = new JTextField(30);
    private JButton button = new JButton("Show");

    private JPanel middleResultField = new JPanel();
    private List<JLabel> weathers = new ArrayList<>();

    public static WeatherGui getInstance() {
        if (gui == null) {
            gui = new WeatherGui();
        }
        return gui;
    }

    /**
     * Constructor sets the configuration of display the GUI.
     */
    private WeatherGui() {
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        middleResultField.setLayout(new BoxLayout(middleResultField, BoxLayout.Y_AXIS));
        middleResultField.setBackground(Color.lightGray);
        middleResultField.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        bottomInfo.add(info);

        ActionListener listener = new SearchListener();
        searchField.addActionListener(listener);
        button.addActionListener(listener);
        topSearchField.add(text);
        topSearchField.add(searchField);
        topSearchField.add(button);

        background.add(BorderLayout.NORTH, topSearchField);
        background.add(BorderLayout.SOUTH, bottomInfo);
        background.add(BorderLayout.CENTER, middleResultField);
    }

    /**
     * Sets another configurations and starts the GUI.
     * Creates list of weather resources that would displays results.
     *
     * @param forecast object that contains all api resources. All work for gets results does through the object.
     */
    public void go(WeatherForecast forecast) {
        setForecast(forecast);

        for (JLabel row : weathers) {
            middleResultField.add(row);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(background);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Sets forecast fiels by WeatherForecast object.
     * Sets in Bottom area how many resources is available.
     * @param forecast object that contains all api resources
     */
    private void setForecast(WeatherForecast forecast) {
        this.forecast = forecast;
        info.setText(String.format("available services %d/%d", forecast.getAvailableResources(), forecast.getPassedResources()));

        List<ForecastObject> services = forecast.getListOfServices();
        for (ForecastObject obj : services) {
            JLabel responseLabel = new JLabel(obj.getRESOURCE());
            responseLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            weathers.add(responseLabel);
        }

    }

    /**
     * Listener for TextField and Button.
     * Works when user sends a data.
     */
    public class SearchListener implements ActionListener {
        /**
         * Sends line that user has sent and sets results in the Middle area.
         *
         * @param e event that occurred
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String city = searchField.getText().trim();

            List<ForecastObject> objects = forecast.getForecast(city);

            for (int i = 0; i < objects.size(); ++i) {
                JLabel label = weathers.get(i);
                label.setText(formResult(objects.get(i)));
            }

            searchField.setText("");
        }

        /**
         * @param obj object that contains all response data.
         * @return the result string.
         */
        private String formResult(ForecastObject obj) {
            StringBuilder builder = new StringBuilder();
            builder.append(obj.getRESOURCE()).append("    -    ");

            if (obj.getRESPONSE() == 200) {
                builder.append(obj.getCITY()).append(": ").append(obj.getWEATHER()).append(" ").append(obj.getTEMPERATURE()).append("C˚");
            } else {
                builder.append("city has not been found");
            }

            return builder.toString();
        }

    }

}
