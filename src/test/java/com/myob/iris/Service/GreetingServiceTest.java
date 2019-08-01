package com.myob.iris.Service;

import com.myob.iris.Model.User;
import com.myob.iris.Service.GreetingService;
import com.myob.iris.Service.UserRepository;
import com.myob.iris.Service.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreetingServiceTest {
    private static List<User> users;
    private ZonedDateTime testDate;
    private GreetingService greetingService;
    private UserRepository userRepository;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        userRepository = mock(UserRepositoryImpl.class);
        when(userRepository.getUsers()).thenReturn(users);
        greetingService = new GreetingService(userRepository);
        testDate = ZonedDateTime.ofInstant(new Date(1561078257000L).toInstant(), ZoneId.of("Australia/Melbourne"));
    }

    @Test
    public void givenOneUserShouldReturnFormattedGreeting() {
        String expected = "Hello Iris - the time on the server is 10:50am on 21 June 2019";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTime() {
        testDate = ZonedDateTime.ofInstant(new Date(1461078834000L).toInstant(), ZoneId.of("Australia/Melbourne"));
        String expected = "Hello Iris - the time on the server is 01:13am on 20 April 2016";
        String actual = greetingService.buildGreeting(testDate);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenOneUserShouldReturnUserNameAndServerTimePm() {
        testDate = ZonedDateTime.ofInstant(new Date(1561088949000L).toInstant(), ZoneId.of("Australia/Melbourne"));
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
