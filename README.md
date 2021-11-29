# Настройки добавленные в pom.xml для данного проекта
```xml
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <failIfNoTests>true</failIfNoTests>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.5</version>
                <executions>
                    <execution>
                        <id>agent-Smith</id>
                        <phase>initialize</phase>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report-agent-Smith</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
# Код Java находящийся в этом репозитории
```Java
package ru.netology.domain;

public class Radio {
    private String name;
    private boolean on;
    private int minRadioStationNumber;
    private int maxRadioStationNumber;
    private int currentRadioStationNumber;
    private int minVolume;
    private int maxVolume;
    private int currentVolume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getMinRadioStationNumber() {
        return minRadioStationNumber;
    }

    public void setMinRadioStationNumber(int minRadioStationNumber) {
        this.minRadioStationNumber = minRadioStationNumber;
    }

    public int getMaxRadioStationNumber() {
        return maxRadioStationNumber;
    }

    public void setMaxRadioStationNumber(int maxRadioStationNumber) {
        this.maxRadioStationNumber = maxRadioStationNumber;
    }

    public int getCurrentRadioStationNumber() {
        return currentRadioStationNumber;
    }

    //Ограничение на вводимый в ручную номер радиостанции в setter:
    public void setCurrentRadioStationNumber(int currentRadioStationNumber) {
        if (currentRadioStationNumber < minRadioStationNumber) {
            this.currentRadioStationNumber = minRadioStationNumber;
            return;
        }
        if (currentRadioStationNumber > maxRadioStationNumber) {
            this.currentRadioStationNumber = maxRadioStationNumber;
            return;
        }
        this.currentRadioStationNumber = currentRadioStationNumber;
    }

    public int getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(int minVolume) {
        this.minVolume = minVolume;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    //Ограничение на вводимый в ручную уровень громкости радиостанции в setter:
    public void setCurrentVolume(int currentVolume) {
        if (currentVolume < minVolume) {
            this.currentVolume = minVolume;
            return;
        }
        if (currentVolume > maxVolume) {
            this.currentVolume = maxVolume;
            return;
        }
        this.currentVolume = currentVolume;
    }

    //Предыдущая радистанция:
    public void pervRadioStation() {
        if (currentRadioStationNumber == minRadioStationNumber) {
            currentRadioStationNumber = maxRadioStationNumber;
        } else {
            currentRadioStationNumber--;
        }
    }

    //Следующая радистанция:
    public void nextRadioStation() {
        if (currentRadioStationNumber == maxRadioStationNumber) {
            currentRadioStationNumber = minRadioStationNumber;
        } else {
            currentRadioStationNumber++;
        }
    }

    //Понижение громкости:
    public void decreaseVolume() {
        if (currentVolume > minVolume) {
            currentVolume--;
        }
    }

    //Повышение громкости:
    public void increaseVolume() {
        if (currentVolume < maxVolume) {
            currentVolume++;
        }
    }
}
```
```Java
package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RadioTest {

    //Общие данные:
    final Radio radio = new Radio();

    final String name = "Panasonic-Z1";
    final boolean on = true;
    final int minRadioStationNumber = 0;
    final int maxRadioStationNumber = 9;
    final int minVolume = 0;
    final int maxVolume = 10;

    private void initializingSharedData() {
        radio.setName(name);
        radio.setOn(on);
        radio.setMinRadioStationNumber(minRadioStationNumber);
        radio.setMaxRadioStationNumber(maxRadioStationNumber);
        radio.setMinVolume(minVolume);
        radio.setMaxVolume(maxVolume);
    }

    @Test   //Тест на выставление текущего номера радиостанции - позитивный, граничные значения
    void shouldCurrentRadioStationNumberPositiveBoundaryValues() {
        initializingSharedData();

        radio.setCurrentRadioStationNumber(0);
        assertEquals(0, radio.getCurrentRadioStationNumber(), "Позитивное минимальное граничное значение");

        radio.setCurrentRadioStationNumber(9);
        assertEquals(9, radio.getCurrentRadioStationNumber(), "Позитивное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего номера радиостанции - негативный, граничные значения
    void shouldCurrentRadioStationNumberNegativeBoundaryValues() {
        initializingSharedData();

        radio.setCurrentRadioStationNumber(-1);
        assertEquals(0, radio.getCurrentRadioStationNumber(), "Негативное минимальное граничное значение");

        radio.setCurrentRadioStationNumber(10);
        assertEquals(9, radio.getCurrentRadioStationNumber(), "Негативное максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на предыдущий - граничные значения
    void shouldPervRadioStationBoundaryValues() {
        initializingSharedData();

        radio.setCurrentRadioStationNumber(0);
        radio.pervRadioStation();
        assertEquals(9, radio.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radio.setCurrentRadioStationNumber(9);
        radio.pervRadioStation();
        assertEquals(8, radio.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на следующий - граничные значения
    void shouldNextRadioStationBoundaryValues() {
        initializingSharedData();

        radio.setCurrentRadioStationNumber(0);
        radio.nextRadioStation();
        assertEquals(1, radio.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radio.setCurrentRadioStationNumber(9);
        radio.nextRadioStation();
        assertEquals(0, radio.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего уровня громкости - позитивный, граничные значения
    void shouldCurrentVolumePositiveBoundaryValues() {
        initializingSharedData();

        radio.setCurrentVolume(0);
        assertEquals(0, radio.getCurrentVolume(), "Позитивное минимальное граничное значение");

        radio.setCurrentVolume(10);
        assertEquals(10, radio.getCurrentVolume(), "Позитивное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего уровня громкости - негативный, граничные значения
    void shouldCurrentVolumeNegativeBoundaryValues() {
        initializingSharedData();

        radio.setCurrentVolume(-1);
        assertEquals(0, radio.getCurrentVolume(), "Негативное минимальное граничное значение");

        radio.setCurrentVolume(11);
        assertEquals(10, radio.getCurrentVolume(), "Негативное максимальное граничное значение");
    }

    @Test   //Тест на понижение текущего уровня громкости - граничные значения
    void shouldDecreaseVolumeBoundaryValues() {
        initializingSharedData();

        radio.setCurrentVolume(0);
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume(), "Минимальное граничное значение");

        radio.setCurrentVolume(10);
        radio.decreaseVolume();
        assertEquals(9, radio.getCurrentVolume(), "Максимальное граничное значение");
    }

    @Test   //Тест на повышение текущего уровня громкости - граничные значения
    void shouldIncreaseVolumeBoundaryValues() {
        initializingSharedData();

        radio.setCurrentVolume(0);
        radio.increaseVolume();
        assertEquals(1, radio.getCurrentVolume(), "Минимальное граничное значение");

        radio.setCurrentVolume(10);
        radio.increaseVolume();
        assertEquals(10, radio.getCurrentVolume(), "Максимальное граничное значение");
    }
}
```