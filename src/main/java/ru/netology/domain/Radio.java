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