package com.myob.iris;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GreetingService {

    private UserRepository userRepository;

    public GreetingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String buildGreeting(Date date) {
        return "Hello " + formatUserName(userRepository.getUsers()) + " - the time on the server is " + formatDate(date);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mma 'on' dd MMMM YYYY");
        return dateFormatter.format(date).replace("AM", "am").replace("PM","pm");
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
