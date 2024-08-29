# Выпускная работа по направлению «Тестировщик»

Дипломный проект — автоматизационное тестирование с СУБД и API Банка.

[План автоматизации](reports/plan.md)

[Отчет по итогам тестирования](reports/report.md)

[Отчет по итогам автоматизации](reports/results.md)

## Инструкция по запуску

1. Склонировать репозиторий с GitHub для запуска на локальном ПК:

    ```bash
    git clone git@github.com:Taranova-Daria/diplom.git
    ```

2. Открыть папку diplom помощью программы IntelliJ IDEA.

3. Работа с базой данных MySQL.
    1. Запуск контейнера docker:
    
        ```bash
        docker-compose up -d
        ```
    
    2. Запуск приложения:
    
        ```bash
        java '-Dspring.datasource.url=jdbc:mysql://localhost:3306/app' -jar ./artifacts/aqa-shop.jar
        ```
    
    3. Запуск тестов:
    
        ```bash
        ./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3306/app'
        ```
    
    4. Сформировать отчет:
    
        ```bash
        ./gradlew allureReport
        ```
    
    5. Открыть отчет в браузере командой:
    
        ```bash
        ./gradlew allureServe
        ```
    
    6. Остановить контейнер:
    
        ```bash
        docker-compose down
        ```

4. Работа с базой данных PostgreSQL.
    1. Запуск контейнера docker:
    
        ```bash
        docker-compose up
        ```
    
    2. Запуск приложения:
    
        ```bash
        java '-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app' -jar ./artifacts/aqa-shop.jar
        ```
    
    3. Запуск тестов:
    
        ```bash
        ./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'
        ```
    
    4. Сформировать отчет:
    
        ```bash
        ./gradlew allureReport
        ```
    
    5. Открыть отчет в браузере командой:
    
        ```bash
        ./gradlew allureServe
        ```

    6. Остановить контейнер:

        ```bash
        docker-compose down
        ```
