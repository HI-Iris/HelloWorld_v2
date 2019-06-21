package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GreetingBuilderTest {
    private static List<User> users;
    private Date testDate = new Date();

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        testDate.setTime(1561078257000L);
    }

    @Test
    public void giveOneUserShouldReturnFormattedGreeting() {
        String expected = "Hello Iris - the time on the server is 10:50am on 21 June 2019";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, testDate);
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void giveOneUserShouldReturnUserNameAndServerTime() {
        testDate.setTime(1461078834000L);
        String expected = "Hello Iris - the time on the server is 01:13am on 20 April 2016";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, testDate);
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void giveOneUserShouldReturnUserNameAndServerTimePm() {
        testDate.setTime(1561088949000L);
        String expected = "Hello Iris - the time on the server is 01:49pm on 21 June 2019";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, testDate);
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void giveTwoUsersShouldReturnNameAndNameAndServerTime() {
        users.add(new User("Paul"));
        String expected = "Hello Iris and Paul - the time on the server is 10:50am on 21 June 2019";
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, testDate);
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void giveThreeUsersShouldReturnNameNameAndNameAndServerTime() {
        users.add(new User("Bella"));
        users.add(new User("Paul"));
        GreetingBuilder greetingBuilder = new GreetingBuilder(users, testDate);
        String expected = "Hello Iris, Bella and Paul - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingBuilder.buildGreeting();
        assertThat(actual, equalTo(expected));
    }

}
