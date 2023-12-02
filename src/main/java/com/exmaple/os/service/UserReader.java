package com.exmaple.os.service;

import com.exmaple.os.model.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserReader {

    public List<User> read() {
        List<User> userList = new ArrayList<>(10);

        userList.add(new User(1, "John", "Doe", "john.doe@example.com", 30, List.of("reading", "hiking", "traveling")));
        userList.add(new User(2, "Alice", "Smith", "alice.smith@example.com", 25, List.of("coding", "music", "gardening")));
        userList.add(new User(3, "Bob", "Johnson", "bob.johnson@example.com", 35, List.of("cooking", "photography", "fitness")));
        userList.add(new User(4, "Eva", "Brown", "eva.brown@example.com", 0, List.of("painting", "yoga", "movies")));
        userList.add(new User(5, "Michael", "Davis", "michael.davisexample.com", 40, List.of("gardening", "traveling", "writing")));
        userList.add(new User(6, "Sophie", "Miller", "sophie.miller@example.com", 22, List.of("reading", "dancing", "photography")));
        userList.add(new User(4, "Ryan", "Wilson", "ryan.wilson", 33, null));
        userList.add(new User(8, "Olivia", "Clark", "olivia.clark@example.com", 29, List.of("music", "traveling", "painting")));
        userList.add(new User(9, "Daniel", "White", "daniel.white@example", 36, List.of()));
        userList.add(new User(10, "Emma", "Jones", "emma.jones@example.com", null, List.of("movies", "fitness", "reading")));

        return userList;
    }
}
