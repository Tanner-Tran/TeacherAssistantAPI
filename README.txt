# TeacherAssistantAPI
A RESTful API exposing endpoints associated with the base Teacher Assistant application:

Validate login: PUT (/login)
{
  "username" : "someUsername",
  "password" : "somePassword"
}

Create new user: PUT (/createUser)
{
  "firstName" : "someFirstName",
  "lastName" : "someLastName",
  "username" : "someUsername",
  "password" : "somePassword"
}

Get all courses of a teacher: GET (/courses?username=someUsername)
{
    "courseList": 
    [
        {
            "title": "Art101",
            "code": "1234"
        },
        {
            "title": "Biology 101",
            "code": "7890"
        }
    ]
}

Create new course for a teacher: PUT (/createCourse?username=someUsername)
{
  "title" : "someCourseTitle",
  "code" : "someCourseCode"
}

Delete a course of a teacher: DELETE (deleteCourse?username=someUsername&courseCode=someCourseCode)

