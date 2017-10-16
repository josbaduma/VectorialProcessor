/**********************************************/
/*  Instituto Tecnológico de Costa Rica       */
/*  Ingeniería en Computadores                */
/*  Arquitectura de Computadores II           */
/*  II Semestre 2017                          */
/*                                            */
/*  Author: José Daniel Badilla Umaña         */
/*  Carné: 201271708                          */
/**********************************************/
package com.architecture.projects.components;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jose
 */
public class Clock extends Observable {

    private final Timer timer;
    private final TimerTask task;
    private boolean clock;
    private static Clock instance;

    public Clock() {
        timer = new Timer();

        clock = false;

        task = new TimerTask() {
            /**
             * Método al que Timer llamará cada milisegundo. Se encarga de
             * avisar a los observadores de este modelo.
             */
            @Override
            public void run() {
                clock = !clock;
                setChanged();
                notifyObservers();
            }
        };

        timer.scheduleAtFixedRate(task, 0, 2);
    }

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }
        return instance;
    }

    public boolean isClock() {
        return clock;
    }
}
