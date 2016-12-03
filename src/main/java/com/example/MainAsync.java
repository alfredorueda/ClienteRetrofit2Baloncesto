package com.example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Navy on 03/11/2016.
 */
public class MainAsync {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JugadorService jugadorService = retrofit.create(JugadorService.class);

        //Hacemos un GET para obtener todos los jugadores
        Call<List<Jugador>> callJugadoresAll = jugadorService.getAllJugadores();
        callJugadoresAll.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener un jugador por ID
        Call<Jugador> callJugador = jugadorService.getJugador(1L);
        callJugador.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET jugador por ID: " + response.body());
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un POST para crear un nuevo jugador
        //Creamos un nuevo jugador
        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNombre("retrofit");
        nuevoJugador.setCanastas(12);
        nuevoJugador.setRebotes(7);
        nuevoJugador.setAsistencias(10);
        nuevoJugador.setPosicion(Posicion.Alero);

        Call<Jugador> callCrearJugador = jugadorService.createJugador(nuevoJugador);
        callCrearJugador.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "POST para crear un nuevo jugador: " + response.body());
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        // TODO: Sleep Thread 5 secs!!
        //Hacemos un PUT para actualizar el jugador creado anteriormente
        //Modificamos unos campos del jugador creado anteriormente
        nuevoJugador.setNombre("Modificado");
        nuevoJugador.setPosicion(Posicion.Escolta);

        Call<Jugador> callUpdateJugador = jugadorService.updateJugador(nuevoJugador);
        callUpdateJugador.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "PUT para actualizar campos del jugador creado anteriormente: " + response.body());
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un DELETE para borrar un jugador por ID
        Call<Void> callDeleteJugador = jugadorService.deleteJugador(1L);
        callDeleteJugador.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "DELETE de un jugador por ID: " + response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener todos los jugadores ordenados por canastas
        Call<List<Jugador>> callJugadoresCanastas = jugadorService.orderByJugadoresCanastas();
        callJugadoresCanastas.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores ordenados por canastas: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener todos los jugadores que tengan mas canastas que el parametro especificado
        Call<List<Jugador>> callJugadoresGreaterCanastas = jugadorService.findByCanastasGreaterThanEqual(2);
        callJugadoresGreaterCanastas.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores que tengan mas canastas que el parametro especificado: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener todos los jugadores que tengan canastas entre el minimo y maximo especificado
        Call<List<Jugador>> callJugadoresMinMaxCanastas = jugadorService.findByCanastasBetween(2, 10);
        callJugadoresMinMaxCanastas.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores que tengan canastas por el rango especificado: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener todos los jugadores con las medias de sus estadisticas
        Call<Map<Posicion, Collection<Jugador>>> callJugadoresMedia = jugadorService.findByPosicionAndMedia();
        callJugadoresMedia.enqueue(new Callback<Map<Posicion, Collection<Jugador>>>() {
            @Override
            public void onResponse(Call<Map<Posicion, Collection<Jugador>>> call, Response <Map<Posicion, Collection<Jugador>>> response) {
                Map<Posicion, Collection<Jugador>> mapJugadoresMedia = response.body();
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores con la media de sus estadisticas: " + mapJugadoresMedia);
            }

            @Override
            public void onFailure(Call<Map<Posicion, Collection<Jugador>>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        //Hacemos un GET para obtener todos los jugadores por posicion, con las medias de sus estadisticas
        Call<Map<Posicion, Collection<Jugador>>> callJugadoresPosicionMedia = jugadorService.findByPosicionAndMedia();
        callJugadoresPosicionMedia.enqueue(new Callback<Map<Posicion, Collection<Jugador>>>() {
            @Override
            public void onResponse(Call<Map<Posicion, Collection<Jugador>>> call, Response <Map<Posicion, Collection<Jugador>>> response) {
                Map<Posicion, Collection<Jugador>> mapJugadoresPosicionMedia = response.body();
                System.out.println("Status code: " + response.code() + System.lineSeparator() +
                        "GET all jugadores por posicion y su media de sus estadisticas: " + mapJugadoresPosicionMedia);
            }

            @Override
            public void onFailure(Call<Map<Posicion, Collection<Jugador>>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
