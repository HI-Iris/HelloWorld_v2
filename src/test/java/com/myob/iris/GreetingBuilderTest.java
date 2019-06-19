package com.myob.iris;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GreetingBuilderTest {
    private static List<User> users;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
    }

    @Test
    public void giveOneUserShouldReturnUserNameAndServerTime(){
        String expect = "Hello Iris - the time on the server is 10:59pm on 14 March 2018";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, "14/03/2018");
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void giveTwoUsersShouldReturnNameAndNameAndServerTime(){
        users.add(new User("Paul"));
        String expect = "Hello Paul and Iris - the time on the server is 10:59pm on 14 March 2018";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, "14/03/2018");
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expect));
    }

    @Test
    public void giveThreeUsersShouldReturnNameNameAndNameAndServerTime(){
        users.add(new User("Paul"));
        users.add(new User("Bella"));
        String expect = "Hello Bella, Paul and Iris - the time on the server is 10:59pm on 14 March 2018";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, "14/03/2018");
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expect));
    }


}
