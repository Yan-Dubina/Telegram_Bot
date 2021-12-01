FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test.javarush_community_bot
ENV BOT_TOKEN=2003545465:AAGZ112DLTIkPvy6TwznFfY-zFbV-uFPI-Y
ENV BOT_DB_USERNAME=telegram_db_user
ENV BOT_DB_PASSWORD=telegram_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
