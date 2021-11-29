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