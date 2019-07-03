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
    private GreetingBuilder greetingBuilder;

    @Before
    public void setup() {
        greetingBuilder = new GreetingBuilder();
        users = new ArrayList<>();
        users.add(new User("Iris"));
        testDate.setTime(1561078257000L);
    }

    @Test
    public void givenOneUserShouldReturnFormattedGreeting() {
        String expected = "Hello Iris - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingBuilder.buildGreeting(users, testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTime() {
        testDate.setTime(1461078834000L);
        String expected = "Hello Iris - the time on the server is 01:13am on 20 April 2016";
        String actual = greetingBuilder.buildGreeting(users, testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTimePm() {
        testDate.setTime(1561088949000L);
        String expected = "Hello Iris - the time on the server is 01:49pm on 21 June 2019";
        String actual = greetingBuilder.buildGreeting(users, testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenTwoUsersShouldReturnNameAndNameAndServerTime() {
        users.add(new User("Paul"));
        String expected = "Hello Iris and Paul - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingBuilder.buildGreeting(users, testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenThreeUsersShouldReturnNameNameAndNameAndServerTime() {
        users.add(new User("Bella"));
        users.add(new User("Paul"));
        String expected = "Hello Iris, Bella and Paul - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingBuilder.buildGreeting(users, testDate);
        assertThat(actual, equalTo(expected));
    }

}
