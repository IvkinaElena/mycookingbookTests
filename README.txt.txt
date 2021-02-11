Рекомендации для запуска тестов:
1. На компьютере должна быть установлена Java версии 1.8+
Инструкция по установке https://www.java.com/ru/download/help/windows_offline_download.html
2. Установить Maven:
 - добавить системную переменную JAVA_HOME, значение = Path, например C:\Program Files\Java\jdk1.8.0_60;
 - скачать файл Maven  apache-maven-3.6.0-bin.zip. с сайта https://maven.apache.org/download.cgi;
 - распаковать архив;
 - добавить системную переменную MAVEN_HOME, значение = Path, в который распакован архив, например C:\Program Files\apache-maven-3.6.3;
 - в системных переменных найти Path, добавить %MAVEN_HOME%\bin
3. Запустить командную строку из директории с проектом.
4. Ввести команду для запуска всех тестов mvn -Dtest=mycookingbookTests.** test
5. Ввести команду для запуска allure-отчета mvn allure:serve



