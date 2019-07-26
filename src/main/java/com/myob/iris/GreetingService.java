package com.myob.iris;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class GreetingService {

    private UserRepository userRepository;

    public GreetingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String buildGreeting(ZonedDateTime currentTime) {
        return "Hello " + formatUserName(userRepository.getUsers()) + " - the time on the server is " + formatDate(currentTime);
    }

    private String formatDate(ZonedDateTime currentTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("hh:mma 'on' dd MMMM YYYY");
        return dateFormatter.format(currentTime).replace("AM", "am").replace("PM","pm");
    }

    private String formatUserName(List<User> users) {
        String name = users.get(0).getName();
        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < users.size() - 1; i++) {
            temp.append(", ").append(users.get(i).getName());
        }
        return users.size() > 1 ? name + temp.append(" and ").append(users.get(users.size() - 1).getName()) : name;
    }

}
