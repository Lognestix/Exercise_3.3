# Настройки добавленные в pom.xml для данного проекта
```xml
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

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

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>		
    </dependencies>
```
# Код Java находящийся в этом репозитории
```Java
package ru.netology.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Radio {
    private int radioId;
    private String name = "Panasonic-Z1";
    private boolean on = true;
    private int amountRadioStation = 10;
    private int minRadioStationNumber = 0;
    private int maxRadioStationNumber = determinationMaxRadioStationNumber(amountRadioStation);
    private int currentRadioStationNumber = minRadioStationNumber;
    private int minVolume = 0;
    private int maxVolume = 100;
    private int currentVolume = (maxVolume + minVolume) / 2;

    public Radio(int amountRadioStation) {
        this.maxRadioStationNumber = determinationMaxRadioStationNumber(amountRadioStation);
    }

    //Расчет максимального номера радиостанций от количества и минимального номера радиостанций
    private int determinationMaxRadioStationNumber(int amountRadioStation) {
        if (amountRadioStation <= 0) {
            amountRadioStation = this.amountRadioStation;
        }
        maxRadioStationNumber = minRadioStationNumber;
        for (int cycle = 1; cycle < amountRadioStation; cycle++) {
            maxRadioStationNumber++;
        }
        return maxRadioStationNumber;
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
    private final Radio radioDefault = new Radio();
    private final Radio radioCustom = new Radio(15);
    private final Radio radioCustomNegative = new Radio(-15);

    @Test   //Тест на выставление текущего номера радиостанции - позитивный, граничные значения
    void shouldCurrentRadioStationNumberPositiveBoundaryValuesRadioDefault() { //Стандартное радио

        radioDefault.setCurrentRadioStationNumber(0);
        assertEquals(0, radioDefault.getCurrentRadioStationNumber(), "Позитивное минимальное граничное значение");

        radioDefault.setCurrentRadioStationNumber(9);
        assertEquals(9, radioDefault.getCurrentRadioStationNumber(), "Позитивное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего номера радиостанции - позитивный, граничные значения
    void shouldCurrentRadioStationNumberPositiveBoundaryValuesRadioCustom() { //Модифицированое радио

        radioCustom.setCurrentRadioStationNumber(0);
        assertEquals(0, radioCustom.getCurrentRadioStationNumber(), "Позитивное минимальное граничное значение");

        radioCustom.setCurrentRadioStationNumber(14);
        assertEquals(14, radioCustom.getCurrentRadioStationNumber(), "Позитивное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего номера радиостанции - негативный, граничные значения
    void shouldCurrentRadioStationNumberNegativeBoundaryValuesRadioDefault() { //Стандартное радио

        radioDefault.setCurrentRadioStationNumber(-1);
        assertEquals(0, radioDefault.getCurrentRadioStationNumber(), "Негативное минимальное граничное значение");

        radioDefault.setCurrentRadioStationNumber(10);
        assertEquals(9, radioDefault.getCurrentRadioStationNumber(), "Негативное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего номера радиостанции - негативный, граничные значения
    void shouldCurrentRadioStationNumberNegativeBoundaryValuesRadioCustom() { //Модифицированое радио

        radioCustom.setCurrentRadioStationNumber(-1);
        assertEquals(0, radioCustom.getCurrentRadioStationNumber(), "Негативное минимальное граничное значение");

        radioCustom.setCurrentRadioStationNumber(15);
        assertEquals(14, radioCustom.getCurrentRadioStationNumber(), "Негативное максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на предыдущий - граничные значения
    void shouldPervRadioStationBoundaryValuesRadioDefault() { //Стандартное радио

        radioDefault.setCurrentRadioStationNumber(0);
        radioDefault.pervRadioStation();
        assertEquals(9, radioDefault.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radioDefault.setCurrentRadioStationNumber(9);
        radioDefault.pervRadioStation();
        assertEquals(8, radioDefault.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на предыдущий - граничные значения
    void shouldPervRadioStationBoundaryValuesRadioCustom() { //Модифицированое радио

        radioCustom.setCurrentRadioStationNumber(0);
        radioCustom.pervRadioStation();
        assertEquals(14, radioCustom.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radioCustom.setCurrentRadioStationNumber(14);
        radioCustom.pervRadioStation();
        assertEquals(13, radioCustom.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на следующий - граничные значения
    void shouldNextRadioStationBoundaryValuesRadioDefault() { //Стандартное радио

        radioDefault.setCurrentRadioStationNumber(0);
        radioDefault.nextRadioStation();
        assertEquals(1, radioDefault.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radioDefault.setCurrentRadioStationNumber(9);
        radioDefault.nextRadioStation();
        assertEquals(0, radioDefault.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест переключения текущего номера радиостанции на следующий - граничные значения
    void shouldNextRadioStationBoundaryValuesRadioCustom() { //Модифицированое радио

        radioCustom.setCurrentRadioStationNumber(0);
        radioCustom.nextRadioStation();
        assertEquals(1, radioCustom.getCurrentRadioStationNumber(), "Минимальное граничное значение");

        radioCustom.setCurrentRadioStationNumber(14);
        radioCustom.nextRadioStation();
        assertEquals(0, radioCustom.getCurrentRadioStationNumber(), "Максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего уровня громкости - позитивный, граничные значения
    void shouldCurrentVolumePositiveBoundaryValues() {

        radioDefault.setCurrentVolume(0);
        assertEquals(0, radioDefault.getCurrentVolume(), "Позитивное минимальное граничное значение");

        radioDefault.setCurrentVolume(100);
        assertEquals(100, radioDefault.getCurrentVolume(), "Позитивное максимальное граничное значение");
    }

    @Test   //Тест на выставление текущего уровня громкости - негативный, граничные значения
    void shouldCurrentVolumeNegativeBoundaryValues() {

        radioDefault.setCurrentVolume(-1);
        assertEquals(0, radioDefault.getCurrentVolume(), "Негативное минимальное граничное значение");

        radioDefault.setCurrentVolume(101);
        assertEquals(100, radioDefault.getCurrentVolume(), "Негативное максимальное граничное значение");
    }

    @Test   //Тест на понижение текущего уровня громкости - граничные значения
    void shouldDecreaseVolumeBoundaryValues() {

        radioDefault.setCurrentVolume(0);
        radioDefault.decreaseVolume();
        assertEquals(0, radioDefault.getCurrentVolume(), "Минимальное граничное значение");

        radioDefault.setCurrentVolume(100);
        radioDefault.decreaseVolume();
        assertEquals(99, radioDefault.getCurrentVolume(), "Максимальное граничное значение");
    }

    @Test   //Тест на повышение текущего уровня громкости - граничные значения
    void shouldIncreaseVolumeBoundaryValues() {

        radioDefault.setCurrentVolume(0);
        radioDefault.increaseVolume();
        assertEquals(1, radioDefault.getCurrentVolume(), "Минимальное граничное значение");

        radioDefault.setCurrentVolume(100);
        radioDefault.increaseVolume();
        assertEquals(100, radioDefault.getCurrentVolume(), "Максимальное граничное значение");
    }

    @Test   //Тест на выставление количество радиостанций при создании объекта - негативный
    void shouldAmountRadioStationNegative() {

        assertEquals(9, radioCustomNegative.getMaxRadioStationNumber());
    }
}
```