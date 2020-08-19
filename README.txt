# TeacherAssistantAPI
A RESTful API exposing all the necessary endpoints for the Teacher Assistant application:

Validate logins: PUT (/login)
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
