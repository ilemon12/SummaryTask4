CREATE DATABASE IF NOT EXISTS electivedb;

CREATE TABLE electivedb.courses(
	courses.id_course INT NOT NULL AUTO_INCREMENT,
    courses.name VARCHAR(50) NOT NULL,
    courses.start_date DATE NOT NULL,
    courses.end_date DATE NOT NULL,
    courses.duration_day INT NOT NULL,
    courses.number_participants INT NOT NULL,
    courses.number_registered INT NOT NULL,
    PRIMARY KEY(id_course)
);  

CREATE TABLE topics(
	topic_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY(topic_id)
);  

CREATE TABLE topics_of_courses(
	topic_id INT NOT NULL,
    course_id INT NOT NULL,
    CONSTRAINT fk_PerTopics FOREIGN KEY(topic_id)
    REFERENCES topics(topic_id),
    CONSTRAINT fk_PerCourses FOREIGN KEY(course_id)
    REFERENCES courses(id_course)
);

CREATE TABLE users(
	user_id INT NOT NULL AUTO_INCREMENT,
    surname VARCHAR(40) NOT NULL,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL,
    pass VARCHAR(40) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY(user_id)
);    


CREATE TABLE courses_of_lecturer(
	id_course INT NOT NULL,
    lecturer_id INT NOT NULL,
    CONSTRAINT fk_PerCourse FOREIGN KEY(id_course)
    REFERENCES courses(id_course),
    CONSTRAINT fk_PerLecturer FOREIGN KEY(lecturer_id)
    REFERENCES users(user_id)
);

CREATE TABLE journal(
	student_id INT NOT NULL,
    course_id INT NOT NULL,
    evaluation INT default 0,
    CONSTRAINT fk_PerStudent FOREIGN KEY(student_id)
    REFERENCES users(user_id),
	CONSTRAINT fk_PerCours FOREIGN KEY(course_id)
    REFERENCES courses(id_course)
);

CREATE TABLE students_blocked(
	student_id INT NOT NULL,
    block BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_PerBlock FOREIGN KEY(student_id)
    REFERENCES users(user_id)
);  