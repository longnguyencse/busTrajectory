Build and Deployment
Ideally, for any non-trivial use case, you should build your client and server independently, and deploy client bundles (static resources) to your choice of web server such as Apache, NGINX, IIS etc., and application server running your choice of servlet container such as Tomcat, Jetty, etc.
However, if you need something quick, you can use the following steps to generate a single deployable war file.

1. Build Client - Go to “demo-app-client” project directory and run “ng build -prod” command.

2. Build Server to generate a deployable war. We can do this by right clicking on the “demo-app-server” project” and clicking Run As -> Maven Install. But before we do this, we have to modify our pom.xml to include static resources from the “demo-app-client” project as shown below.

<plugin>
<artifactId>maven-resources-plugin</artifactId>
<executions>
      <execution>
          <id>copy-resources</id>
          <phase>validate</phase>
          <goals><goal>copy-resources</goal></goals>
          <configuration>
              <outputDirectory>${basedir}/target/classes/static/</ outputDirectory >
              <resources>
                  <resource>
                      <directory>${basedir}/../ demo-app-client/dist</ directory >
                  </resource>
              </resources>
          </configuration>
      </execution>
</executions>
</plugin>


Once the build is complete, just drop the generated war file into any servlet container and have fun!

A few things to watch out for:

1. URL bookmarking will not work. You will either always have to start from the home page or you will need to add extra configuration within the project to resolve urls at the Spring MVC level.

2. Eclipse’s Typescript plug-in is not that great, but I’m hoping that support will improve down the road.