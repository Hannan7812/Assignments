# Library Management System
## Assignment 1: Object Oriented Programming
# Library Management System

This project is part of Assignment 1 for the Object Oriented Programming course.

## Description

The Library Management System is a software application designed to manage the operations of a library. It provides functionalities such as adding and removing books, managing library members, borrowing and returning books, and generating reports.

## Features

- Add new books to the library
- Remove books from the library
- Manage library members
- Borrow and return books
- Generate reports

## Dependencies
The program requires several components to run properly. To run on any machine, it would require the Java virtual machine. Furthermore, It would also require a valid connection with a mysql server. The database must have the following format
#### Books Table
- Must contain columns for Bookid, title, author, genre, avaialble
#### Users Table
- Must contain columns for Userid, name, contact
#### Borrows Table
- Must contain Bookid and Userid columns, both of which must be foreign keys referring to their respective tables
In case of a different choice of Database, the queries in the program must be altered. 

## Installation

1. Clone the repository: `git clone https://github.com/your-username/library-management-system.git`
2. Install the required dependencies: `npm install`



