package com.example.response;

import com.example.entity.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {
    private Long id;
    private Double marksObtained;
    private String subjectName;
    public SubjectResponse(Subject subject){
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.marksObtained = subject.getMarksObtained();
    }
}
