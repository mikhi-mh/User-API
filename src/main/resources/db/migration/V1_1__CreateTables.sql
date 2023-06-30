CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      middle_name VARCHAR(255),
                      last_name VARCHAR(255) NOT NULL,
                      date_of_birth DATE NOT NULL,
                      gender VARCHAR(255),
                      mobile_number VARCHAR(255) NOT NULL,
                      email_id VARCHAR(255) NOT NULL,
                      address VARCHAR(255) NOT NULL,
                      date_of_admission DATE,
                      role VARCHAR(255),
                      subject VARCHAR(255)
);
