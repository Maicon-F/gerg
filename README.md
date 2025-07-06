# GERG 2008 Java Implementation

## Overview
This project is an implementation of the GERG 2008 Equation of State (EOS). It is originally described and parametrized in the article by [O. Kunz and W. Wagner ](https://pubs.acs.org/doi/10.1021/je300655b). Although primarily an educational tool, it applies advanced chemical engineering concepts to real-world scenarios using Java, providing a reliable way to obtain physical-chemical properties used in industrial projects and activities.
<br>



<br>


<h2><a>How it looks like</h2>
<img alt="img" src="https://github.com/user-attachments/assets/cfe08c0f-8940-4e04-ba33-43d10dbc0fe9"/>  
<br>
<a href="https://www.youtube.com/watch?v=KARdi4OknF0">✅Check an example!✅</a> 

<br>
<h2>Easy Run with Docker</h2>
In case you only need the current docker image, it is available on: https://hub.docker.com/repository/docker/maiconf/gerg2008/general
<br>

<h2><a>Development information</h2>  
If you want to make any changes on the application, JDK 22 will be required to compile it, and Docker creates the image and runs the container with all functionalities needed to run Gerg 2008. Everything is handled by docker-compose, from creating the database container and the connection to the applicaiton container.  
<br>
After installing JDK22 and Docker on your local environment, all you need to do is to run the followig command:
  
**sudo ./restart-docker.sh**  
<br>
  

<h2>More Substances</h2>
The database is only populated with data relative to pentane and CO2, in case you actually need the full catalog with other substances and mixtures, you may contact me.
<br>

<hr>
<br>
<br>

<h2>Tools</h2>
- Docker
- JDK 22
- Springboot
- Vaadin
- Postgress | H2






####  [Maicon Fernandes]( https://www.linkedin.com/in/maicon-fernandes/)


