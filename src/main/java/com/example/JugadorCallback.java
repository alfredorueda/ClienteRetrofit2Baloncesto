package com.example;

import java.util.List;

/**
 * Created by 46419674Q on 04/11/2016.
 */
public interface JugadorCallback {

    void onSuccess();

    void onSuccess(List<Jugador> jugadorList);

    void onSuccess(Jugador jugador);

    void onFailure(Throwable t);
}

