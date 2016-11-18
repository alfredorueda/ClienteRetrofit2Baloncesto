package com.example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.io.*;

/**
 * Created by Navy on 04/11/2016.
 */
public class JugadorManager {
    public static final String BASE_URL = "http://localhost:8080";
    private List<Jugador> jugadores;
    private Retrofit retrofit;
    private JugadorService jugadorService;
    private static JugadorManager jugadorManager;

    public JugadorManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jugadorService = retrofit.create(JugadorService.class);

    }

    public synchronized void getAllJugadores(JugadorCallback jugadorCallback){
        Call<List<Jugador>> callJugadoresAll = jugadorService.getAllJugadores();
        callJugadoresAll.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                jugadores = response.body();

                if(response.isSuccessful()){
                    jugadorCallback.onSuccess(jugadores);
                }
            }

            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
               System.out.print("JugadorManager -> getAllJugadores() -> ERROR " + t);
                jugadorCallback.onFailure(t);

            }
        });
    }

    public synchronized void crearJugador(Jugador jugador, JugadorCallback jugadorCallback){
        Call<Jugador> callCrearJugador = jugadorService.createJugador(jugador);
        callCrearJugador.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                int codigo = response.code();

                if(response.isSuccessful()){
                    jugadorCallback.onSuccess(response.body());
                }else{
                    jugadorCallback.onFailure(new Throwable("ERROR al crear el jugador" + codigo + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                System.out.print("JugadorManager -> crearJugador() -> ERROR " + t);
                jugadorCallback.onFailure(t);
            }
        });
    }

    public synchronized void updateJugador(Jugador jugador ,final JugadorCallback jugadorCallback){
        Call<Jugador> callUpdateJugador = jugadorService.updateJugador(jugador);
        callUpdateJugador.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                int codigo = response.code();

                if(response.isSuccessful()){
                    jugadorCallback.onSuccess(response.body());
                }else{
                    jugadorCallback.onFailure(new Throwable("ERROR al hacer update del jugador" + codigo + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                System.out.println("JugadorManager -> updateJugador() -> ERROR " + t);
            }
        });
    }

    public synchronized void deleteJugador(Jugador jugador, final JugadorCallback jugadorCallback){
        Call<Void> callDeleteJugador = jugadorService.deleteJugador(1L);
        callDeleteJugador.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int codigo = response.code();

                if(response.isSuccessful()){
                    jugadorCallback.onSuccess(jugador);
                }else{
                    jugadorCallback.onFailure(new Throwable("ERROR al borrar el jugador" + codigo + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("JugadorManager -> deleteJugador() -> ERROR " + t);
            }
        });
     }

    public synchronized void getJugador(Jugador jugador, final JugadorCallback jugadorCallback){
        Call<Jugador> callGetJugador = jugadorService.getJugador(1L);
            callGetJugador.enqueue(new Callback<Jugador>() {
                @Override
                public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                    int codigo = response.code();

                    if(response.isSuccessful()){
                        jugadorCallback.onSuccess(response.body());
                    }else{
                        jugadorCallback.onFailure(new Throwable("ERROR al obtener un jugador" + codigo + response.raw().message()));
                    }
                }

                @Override
                public void onFailure(Call<Jugador> call, Throwable t) {
                    System.out.println("JugadorManager -> getJugador() -> ERROR " + t);
                }
            });
        }
    }

