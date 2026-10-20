## STACK VISUALIZATION SYSTEM

Group 2

Urgel, Clive Andrei C.

Torres, Jobaille Grace

Dela Torre, Mery Grace

Lumanta, Adrian Dave

# Description
Our project is a Stack Visualization System developed using Java. The purpose of this program is to demonstrate how a stack works using the Last-In, First-Out (LIFO) principle. Instead of showing the stack through text only, we created a graphical interface where users can see how elements are added and removed. This project also helps us understand how stack operations work in an actual program.The Stack Visualization System is a Java Swing application that demonstrates how an array-based stack works. It follows the Last-In, First-Out (LIFO) principle and displays stack elements as graphical blocks

# Features
Our program includes the basic stack operations and other features that make the application easier to use.

Push – Adds a new value to the top of the stack.

Pop – Removes the top element from the stack.

Peek – Shows the top element without removing it.

Clear – Removes all elements from the stack.

Check Stack – Checks whether the stack is empty or not.

Random – Generates random values inside the stack.

Stack Visualization – Displays the elements as blocks so users can easily understand their positions.

Error Messages – Displays messages when the stack is full or empty.

# Requirements
To run our program, the following are needed:

Java Development Kit (JDK)

Apache NetBeans IDE

Java Swing and AWT

A computer that supports Java applications

# How to Run
Download or clone our project from GitHub.

Open Apache NetBeans.

Open the downloaded project.

Make sure the Java files are properly included.

Build and run the program.

Enter a number and use the buttons to perform different stack operations.

Observe how the stack changes after every operation.

# Project Structure
Our project is divided into two main Java classes. The first class is responsible for the stack operations, while the second class handles the graphical user interface.

StackADT.java – This class manages the stack using an array. It contains the methods for pushing, popping, peeking, checking whether the stack is empty, and clearing the stack.

StackVisualizerGUI.java – This class manages the graphical interface, including the buttons, input field, status messages, and visual representation of the stack.

Separating these classes helps us organize our code and makes it easier to understand and maintain.

# Stack Implementation
Our program uses an array-based stack with a maximum capacity of 10 elements. It follows the LIFO principle, meaning the last value added is the first value removed.

When the user clicks PUSH, the value is added to the top of the stack. When POP is clicked, the top element is removed. PEEK allows the user to see the top value without removing it. The program also checks whether the stack is empty or full to prevent invalid operations.

The graphical interface updates whenever an operation is performed so that the displayed stack matches the actual contents of the array.

# Screenshots
This section contains screenshots of our program showing how the different stack operations work.

[Screenshot of the main interface]

[Screenshot of PUSH operation]

[Screenshot of POP operation]

[Screenshot of PEEK operation]

[Screenshot of RANDOM operation]

[Screenshot of CLEAR operation]

[Screenshot of overflow and underflow messages]

# AI Disclosure
Our group used ChatGPT as a guide while working on this project. It helped us understand the stack data structure, organize our program, and learn how some Java methods work. We also used it to get suggestions for improving our code and documentation.

We are responsible for reviewing, understanding, and testing the code before submitting our final project.

# Contributors
This project was developed as a group activity. Each member is expected to contribute to the programming, testing, debugging, documentation, or improvement of the application. We also use GitHub to manage our project and track the changes made by each member.
