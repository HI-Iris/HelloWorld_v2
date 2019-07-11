package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreetingServiceTest {
    private static List<User> users;
    private Date testDate = new Date();
    private GreetingService greetingService;
    private UserRepository userRepository;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        userRepository = mock(UserRepositoryImpl.class);
        when(userRepository.getUsers()).thenReturn(users);
        greetingService = new GreetingService(userRepository);
        testDate.setTime(1561078257000L);
    }

    @Test
    public void givenOneUserShouldReturnFormattedGreeting() {
        String expected = "Hello Iris - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTime() {
        testDate.setTime(1461078834000L);
        String expected = "Hello Iris - the time on the server is 01:13am on 20 April 2016";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTimePm() {
        testDate.setTime(1561088949000L);
        String expected = "Hello Iris - the time on the server is 01:49pm on 21 June 2019";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenTwoUsersShouldReturnNameAndNameAndServerTime() {
        users.add(new User("Paul"));
        String expected = "Hello Iris and Paul - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenThreeUsersShouldReturnNameNameAndNameAndServerTime() {
        users.add(new User("Bella"));
        users.add(new User("Paul"));
        String expected = "Hello Iris, Bella and Paul - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

}