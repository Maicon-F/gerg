# GERG 2008 Java Implementation

## Overview
This project is an educational (and only educational) implementation of the GERG 2008 Equation of State (EOS), originally developed by [O. Kunz and W. Wagner ](https://pubs.acs.org/doi/10.1021/je300655b). Although primarily an educational tool, it applies advanced chemical engineering concepts to real-world scenarios using Java. The goal is to improve my Java skills while providing a useful tool for the community.


<h2><a>How it looks like</h2>
<img alt="img" src="https://github.com/user-attachments/assets/cfe08c0f-8940-4e04-ba33-43d10dbc0fe9"/>
  
<a href="https://www.youtube.com/watch?v=KARdi4OknF0">✅Check an example!✅</a> 

<h2>How to use it</h2>
You need to have JDK 22 and Postgres database to run it without further ado. However, other databases can be used, as long as it is a relational database and the right modifications on the application.properties are done. I have provided a sql txt file in order to populate carbon dioxide and pentane parameters, other substances must be populated manually for now. Before running the sql, load the classes using JPA|Hibernate manager by running the app, otherwise a error message should be expected. 
<hr>
<hr>
I have also created a executable version of the software in order to provide it in the easiest way possible. No previous configuration, database or any other type of installation is necessary. Just download it from this 
<a href="https://drive.google.com/file/d/1w2KcSEiZ0rWgA3vpYFkdhKfjb3Av-E_D/view?usp=drive_link">link</a> 
.Then, click on "Run Gerg", allow it to run, and a web page will open on localhost:8080.


<h2>Tools</h2>
- JDK 22
- Springboot
- Vaadin
- Postgress | H2

<h4>What it doesnt have yet</h4>
- JWT or any other type of authentication
- multiple threads
- Second level cache
- Cloud availability




####  [Maicon Fernandes]( https://www.linkedin.com/in/maicon-fernandes/)


