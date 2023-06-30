package com.csv.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    //region Attributes or Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;


    @NotBlank(message = "First name must not be blank")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Date of Birth should not be null")
    @PastOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy" )
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date of Birth must be in the format dd/MM/yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Transient
    private Double age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "^\\+91\\d{10}$", message = "Mobile number must be in the format +91XXXXXXXXXX")
    @Column(name = "mobile_number")
    private String mobileNumber;

    @NotBlank(message = "Email-Id must not be blank")
    @Email
    @Column(name = "email_id")
    private String emailId;

    @NotBlank(message = "Address must not be blank")
    @Column(length = 255)
    private String address;

    //@NotNull(message = "Date of Admission should not be null")
    @PastOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy" )
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Date of Admission must be in the format dd/MM/yyyy")
    @Column(name = "date_of_admission")
    private LocalDate dateOfAdmission;

//endregion

    //region other Mthods
    @PostLoad
    public void calculateAge(){
        if(dateOfBirth != null){
            LocalDate today = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(dateOfBirth, today);
            this.age = daysBetween / 365.25;
        }
    }
    //endregion
}
