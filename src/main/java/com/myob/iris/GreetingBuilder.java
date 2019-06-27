package com.myob.iris;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GreetingBuilder {

    public String buildGreeting(List<User> users, Date date) {
        return "Hello " + formatUserName(users) + " - the time on the server is " + formatDate(date);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mma 'on' dd MMMM YYYY");
        return dateFormatter.format(date);
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
