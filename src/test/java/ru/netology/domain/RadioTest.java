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