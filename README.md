# GERG 2008 Java Implementation

## Overview
This project is an educational (and only educational) implementation of the GERG 2008 Equation of State (EOS), originally developed by [O. Kunz and W. Wagner ](https://pubs.acs.org/doi/10.1021/je300655b). Although primarily an educational tool, it applies advanced chemical engineering concepts to real-world scenarios using Java. The goal is to improve my Java skills while providing a useful tool for the community.
<br>
<br>
<h2><a>How it looks like</h2>
<img alt="img" src="https://github.com/user-attachments/assets/cfe08c0f-8940-4e04-ba33-43d10dbc0fe9"/>
<br>
<a href="https://www.youtube.com/watch?v=KARdi4OknF0">✅Check an example!✅</a> 
<br>
<h2>How to use it</h2>
You need Docker and JDK 22 to compile the application and create its image. Everything else will be handled by docker-compose, from creating the database container and the connection to the applicaiton container.  The application will run a run a script during initialization in order to populate data relative to pentane and CO2, in case you actually need the full catalog with other substances and mixtures, you may contact me.

**sudo ./restart-docker.sh**
<hr>
<br>
<br>

<h2>Tools</h2>
- JDK 22
- Springboot
- Vaadin
- Postgress | H2





####  [Maicon Fernandes]( https://www.linkedin.com/in/maicon-fernandes/)


