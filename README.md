<h1 align="center">
PhoneBook
</h1>
<p align="center">
  <a href="https://github.com/erickfunier">
    <img alt="Made by Erick Santos" src="https://img.shields.io/badge/made%20by-Erick%20Santos-lightgrey">
  </a>
</p>
<p>
    This is a simple Java application using OOP concepts, this application is 
    a Phonebook using CLI and storage in memory or file.
</p>
<h2>Dependencies</h2>
<p>
    JRE 11
</p>

<h2>Running the application</h2>
<h3>With Docker</h3>

    docker build -t phonebook .
    docker run --rm -ti phonebook

<h3>With JRE</h3>
<p>Run the .jar file from the folder from root project folder</p>
     
    java -jar Phonebook.jar

<h2>Environment development</h2>
<p>The application is ready to run in IntelliJ IDE (2021.3), you can import the project from the GitHub repository</p>

<h2>Project Structure</h3>
<h3>controller</h3>
<p>Used to handle actions with contacts</p>

<h3>domain</h3>
<p>The objects used by application, related to business rules</p>

<h3>port</h3>
<p>Ports to interact with application in this case is CLI</p>

<h3>repository</h3>
<p>The repositories implementations, InMemory or InFile. I.E. the interfaces to business rules</p>

<h3>util</h3>
<p>Validators and Error handler</p>

<h3>usecase</h3>
<p>The implementation of use cases related to business rules</p>
