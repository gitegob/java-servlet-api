//package com.gitego.javaservlet.students;
//
//import com.gitego.javaservlet.utils.JsonUtil;
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class StudentService {
//    private final ConcurrentMap<Integer, Student> students;
//    private final AtomicInteger key;
//    private final JsonUtil jsonUtil;
//
//    public StudentService() {
//        this.students = new ConcurrentHashMap<>();
//        this.key = new AtomicInteger();
//        this.jsonUtil = new JsonUtil();
//        this.addStudent(new Student("Brian", 70, "B"));
//        this.addStudent(new Student("Bruce", 80, "A"));
//        this.addStudent(new Student("Ben", 75, "B+"));
//        this.addStudent(new Student("Smith", 90, "A+"));
//    }
//
//    public String findStudents() {
//        List<Student> students = new ArrayList<>(this.students.values());
//        return jsonUtil.toJson(students);
//    }
//
//    public Student createStudent(String jsonPayload) {
//        if (jsonPayload == null) return null;
//        Gson gson = new Gson();
//        try {
//            Student newStudent = gson.fromJson(jsonPayload, Student.class);
//            if (newStudent != null) {
//                return this.addStudent(newStudent);
//            }
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }
//
//    private Student addStudent(Student student) {
//        student.setId(key.incrementAndGet());
//        this.students.putIfAbsent(student.getId(), student);
//        return student;
//    }
//}
