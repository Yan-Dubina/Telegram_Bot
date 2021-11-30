FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=babyan_bot
ENV BOT_TOKEN=2003545465:AAGZ112DLTIkPvy6TwznFfY-zFbV-uFPI-Y
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dbot.username=${BOT_NAME}","-Dbot.token=${BOT_TOKEN}","-jar","/app.jar"]