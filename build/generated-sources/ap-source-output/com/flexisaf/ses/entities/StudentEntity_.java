package com.flexisaf.ses.entities;

import com.flexisaf.ses.entities.GenderType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-31T19:58:44")
@StaticMetamodel(StudentEntity.class)
public class StudentEntity_ { 

    public static volatile SingularAttribute<StudentEntity, Long> studentID;
    public static volatile SingularAttribute<StudentEntity, String> lastName;
    public static volatile SingularAttribute<StudentEntity, String> firstName;
    public static volatile SingularAttribute<StudentEntity, String> emailAddress;
    public static volatile SingularAttribute<StudentEntity, Long> creatorUserID;
    public static volatile SingularAttribute<StudentEntity, Long> departmentID;
    public static volatile SingularAttribute<StudentEntity, String> matricNumber;
    public static volatile SingularAttribute<StudentEntity, String> otherName;
    public static volatile SingularAttribute<StudentEntity, Date> dateOfBirth;
    public static volatile SingularAttribute<StudentEntity, GenderType> genderType;
    public static volatile SingularAttribute<StudentEntity, Date> createDate;

}