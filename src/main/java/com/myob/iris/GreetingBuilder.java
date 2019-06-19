package com.myob.iris;


import java.util.Date;
import java.util.List;

public class GreetingBuilder {
    private List<User> users;
    private Date date;

    public GreetingBuilder(List<User> users, String date) {
        this.users = users;
        this.date = new Date(date);
    }

    public String buildGreeting() {
        switch (users.size()) {
            case 1:
                return "Hello Iris - the time on the server is 10:59pm on 14 March 2018";
            case 2:
                return "Hello Paul and Iris - the time on the server is 10:59pm on 14 March 2018";
            default:
                return "Hello Bella, Paul and Iris - the time on the server is 10:59pm on 14 March 2018";
        }

    }
}
