package com.example.Consultancy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Consultancy.entity.student;
import com.example.Consultancy.repository.StudentRepository;

@RestController
public class StudentController {
	
	//automatically creating objects when required,in here it is automatically doing it for instance varibale studentRepo
	@Autowired
	StudentRepository studentRepo;
	
	//sending input from the user to the db
    // Create or update a student
    @PostMapping("/api/students")
    public ResponseEntity<student> saveStudent(@RequestBody student student) {
        return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
    }
    
    @PostMapping("/api/students/batchIntake")
    public ResponseEntity<List<student>> saveStudents(@RequestBody List<student> students) {
        return new ResponseEntity<>(studentRepo.saveAll(students),HttpStatus.CREATED);
    }

    
	//getting the data from the db and showing it to user
	@GetMapping("/api/students")
	public ResponseEntity<List<student>> getStudents(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	//here we since we are passing id(argument) its called path path param)
	//if the id is present we are sending it to the user other wise we have to throw some other message for that we use optional
	@GetMapping("/api/students/{id}")
	public ResponseEntity<student> getStudentById(@PathVariable long id){
		Optional<student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		}
	
	//putmapping is diff from postmapping , here we are updating which is differnet from adding
			//also as we are taking i/p from user for updation, we need @Requestbody annotation
	@PutMapping("/api/students/{id}")
	public ResponseEntity<student> updateStudentById(@PathVariable long id, @RequestBody student stud) {
	    Optional<student> existingStudent = studentRepo.findById(id);
	    if (existingStudent.isPresent()) {
	        student student = existingStudent.get();
	        student.setName(stud.getName());
	        student.setEmail(stud.getEmail());
	        student.setAddress(stud.getAddress());
	        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable long id){
		Optional<student> existingStudent = studentRepo.findById(id);
		if(existingStudent.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
