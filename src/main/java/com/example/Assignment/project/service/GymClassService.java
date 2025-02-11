package com.example.Assignment.project.service;

import com.example.Assignment.project.Dto.GymClass;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GymClassService {
    private final ConcurrentHashMap<Long, GymClass> classes = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public GymClass createClass(GymClass gymClass) {
        if (gymClass.getEndDate().isBefore(gymClass.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        gymClass.setId(idGenerator.incrementAndGet());
        classes.put(gymClass.getId(), gymClass);
        return gymClass;
    }

    public GymClass getClass(Long id) {
        GymClass gymClass = classes.get(id);
        if (gymClass == null) {
            throw   new IllegalArgumentException("Class not found");
        }
        return gymClass;
    }
}
