# libr - Book management system
![Capture-removebg-preview](https://user-images.githubusercontent.com/74790139/120447662-7c6c1900-c393-11eb-8799-b6fb2ea7283d.png)

# Intro

libr is an Internet based application that can be accessed throughout the internet and can be accessed by anyone who has an internet connection.
This Web Application includes a system to manage major library-related functionalities such as finding books, issuing books, returning books, hold requests, tracking books, and fine collection.

# Development
The backend of the system is developed on Spring Boot Framework. The front end is built on Angular and Bootstrap.

# Features
There are roles in the application(Admin/User). Users can only access limited features, i.e., public access level features which include searching a book and user registration form. 
After logging in Admin can search for a specific book, book issue from the home panel or can search the specific user from the Users page. A book can be added manually only by an Admin. To automate the process was added Google Books APIs.
Another responsibility of a Admin is to approve user request in situations where was a request for a book, i.e. books requests are to be verified. Admin have a panel to simply approve / reject books requests. The Admin ID is stored alongside each last applied status on book to keep track. The most important function of any library is to issue and return books. This system includes a panel to view all outstanding logs and a super simple panel to issue.

# Scope and mission
See the [Wiki](//github.com/isd-soft/libr/wiki) page.
