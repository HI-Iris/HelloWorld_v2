package com.myob.iris;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GreetingBuilder {
    private List<User> users;
    private Date date;

    public GreetingBuilder(List<User> users, Date date) {
        this.users = users;
        this.date = date;
    }

    public String buildGreeting() {
        return "Hello " + formatUserName() + " - the time on the server is " + formatDate();
    }

    private String formatDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("KK:mma 'on' dd MMMM YYYY");
        return dateFormatter.format(this.date);
    }

    private String formatUserName() {
        String name = users.get(0).getName();
        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < users.size() - 1; i++) {
            temp.append(", ").append(users.get(i).getName());
        }
        return users.size() > 1 ? name + temp.append(" and ").append(users.get(users.size() - 1).getName()) : name;
    }


}
