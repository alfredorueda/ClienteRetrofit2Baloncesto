package com.example;

import retrofit2.Call;
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
public class MainSync {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JugadorService jugadorService = retrofit.create(JugadorService.class);

        //Hacemos una llamada a la api del JugadorService, para llamar al metodo GET y que nos devuelva todos los Jugadores
        Call<List<Jugador>> callJugadoresAll = jugadorService.getAllJugadores();//llamada en potencia de la api del jugadorService
        Response<List<Jugador>> response = callJugadoresAll.execute();//hasta que no hacemos el .execute, no se ejecuta la llamada a la api

        if (response.isSuccessful()) {
            List<Jugador> jugadorList = response.body();
            System.out.println("Status code: " + response.code() + System.lineSeparator() +
                    "GET all jugadores: " + jugadorList);
        } else {
            System.out.println("Status code: " + response.code() +
                    "Mensaje error: " + response.errorBody());
        }

        callJugadoresAll = jugadorService.getError();
        response = callJugadoresAll.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() +
                    " Mensaje de error: " + response.raw() );
        }

        //Creación de un nuevo Jugador
        Jugador jugador = new Jugador();
        jugador.setNombre("retrofit");
        jugador.setCanastas(150);
        Call<Jugador> callJugador = jugadorService.createJugador(jugador);
        Response<Jugador> responseJugador= callJugador.execute();

        //Hacemos el POST para crear el nuevo Jugador
        if(responseJugador.isSuccessful()) {
            Jugador respoJugador = responseJugador.body();
            System.out.println("Status code: " + responseJugador.code() + System.lineSeparator() +
                    "POST de Jugador: " + respoJugador);

            //Hacemos el PUT para modificar el campo puntos del Jugador creado anteriormente
            respoJugador.setCanastas(120);
            callJugador = jugadorService.updateJugador(respoJugador);
            responseJugador = callJugador.execute();

            System.out.println("Status code: " + responseJugador.code() + System.lineSeparator() +
                    "PUT de Jugador: " + responseJugador.body());

            //Hacemos el DELETE para eliminar el Jugador que hemos creado anteriormente
            Call<Void> callDelete= jugadorService.deleteJugador(respoJugador.getId());
            Response<Void> responseDelete= callDelete.execute();

            System.out.println("DELETE de Jugador status code: " + responseDelete.code());

            //Hacemos el GET para obtener todos los Jugadores que tenemos creados ahora mismo
            callJugadoresAll = jugadorService.getAllJugadores();
            response = callJugadoresAll.execute();
            System.out.println("Comprobación del delete de Jugador " +
                    "Status code: " + response.code() + System.lineSeparator() +
                    "GET de Jugadores: " + response.body());
        } else {
            System.out.println("Status code: " + responseJugador.code() +
                    "Mensaje de error: " + responseJugador.errorBody());
        }

        //Realizamos una prueba para hacer un GET de 1 Jugador con un campo especifico y ver si obtenemos respuesta
        callJugador = jugadorService.getJugador(1L);
        responseJugador = callJugador.execute();

        if(responseJugador.isSuccessful()) {
            System.out.println("GET de 1 Jugador->Status code: " + responseJugador.code() +
                    " Jugador: " + responseJugador.body() );
        }

        //Hacemos un GET para obtener todos los jugadores ordenados por canastas
        Call<List<Jugador>> callJugadoresAllCanastas = jugadorService.OrderByJugadoresCanastas();
        Response<List<Jugador>> responseAllCanastas = callJugadoresAllCanastas.execute();

        if (responseAllCanastas.isSuccessful()) {
            List<Jugador> jugadorList = response.body();
            System.out.println("Status code: " + responseAllCanastas.code() + System.lineSeparator() +
                    "GET all jugadores por canastas: " + jugadorList);
        } else {
            System.out.println("Status code: " + responseAllCanastas.code() +
                    "Mensaje error: " + responseAllCanastas.errorBody());
        }

        callJugadoresAllCanastas = jugadorService.getError();
        response = callJugadoresAllCanastas.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() +
                    " Mensaje de error: " + response.raw() );
        }

        //Hacemos un GET para obtener todos los jugadores a partir de un rango de canastas
        Call<List<Jugador>> callJugadoresCanastas = jugadorService.findByCanastasGreaterThanEqual(10);
        Response<List<Jugador>> responseCanastas = callJugadoresCanastas.execute();

        if (responseCanastas.isSuccessful()) {
            List<Jugador> jugadorList = response.body();
            System.out.println("Status code: " + responseCanastas.code() + System.lineSeparator() +
                    "GET all jugadores por un rango de canastas: " + jugadorList);
        } else {
            System.out.println("Status code: " + responseCanastas.code() +
                    "Mensaje error: " + responseCanastas.errorBody());
        }

        callJugadoresCanastas = jugadorService.getError();
        response = callJugadoresCanastas.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() +
                    " Mensaje de error: " + response.raw() );
        }

        //Hacemos un GET para obtener todos los jugadores a partir de un minimo y maximo de canatas
        Call<List<Jugador>> callJugadoresCanastasMinMax = jugadorService.findByCanastasBetween(2, 20);
        Response<List<Jugador>> responseCanastasMinMax = callJugadoresCanastasMinMax.execute();

        if (responseCanastasMinMax.isSuccessful()) {
            List<Jugador> jugadorList = response.body();
            System.out.println("Status code: " + responseCanastasMinMax.code() + System.lineSeparator() +
                    "GET all jugadores por un minimo y maximo de canastas: " + jugadorList);
        } else {
            System.out.println("Status code: " + responseCanastasMinMax.code() +
                    "Mensaje error: " + responseCanastasMinMax.errorBody());
        }

        callJugadoresCanastasMinMax = jugadorService.getError();
        response = callJugadoresCanastasMinMax.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() +
                    " Mensaje de error: " + response.raw() );
        }

        //Hacemos un GET para obtener todos los jugadores por posicion y con sus estadisticas
        Call<Map<Posicion ,Collection<Jugador>>> callJugadoresMedia = jugadorService.findByPosicionAndMedia();
        Response<Map<Posicion, Collection<Jugador>>> responseJugadoresMedia = callJugadoresMedia.execute();

        if (responseJugadoresMedia.isSuccessful()) {
            Map<Posicion, Collection<Jugador>> mapJugadoresPosicionAVG = responseJugadoresMedia.body();
            System.out.println("Status code: " + responseJugadoresMedia.code() + System.lineSeparator() +
                    "GET all jugadores de una posicion y con la media de todas sus estadisticas: " + mapJugadoresPosicionAVG);
        } else {
            System.out.println("Status code: " + responseJugadoresMedia.code() +
                    "Mensaje error: " + responseJugadoresMedia.errorBody());
        }

        /*callJugadoresMedia = jugadorService.getError();
        response = callJugadoresMedia.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + response.code() +
                    " Mensaje de error: " + response.raw() );
        }*/

        //Hacemos un GET para obtener todos los jugadores por posicion y con sus estadisticas
        Call<Map<Posicion ,Collection<Jugador>>> callJugadoresPosicionMedia = jugadorService.findByAllPosiciones();
        Response<Map<Posicion, Collection<Jugador>>> responseJugadoresPosicionMedia = callJugadoresPosicionMedia.execute();

        if (responseJugadoresPosicionMedia.isSuccessful()) {
            Map<Posicion, Collection<Jugador>> mapJugadoresPosicion = responseJugadoresPosicionMedia.body();
            System.out.println("Status code: " + responseJugadoresPosicionMedia.code() + System.lineSeparator() +
                    "GET all jugadores de una posicion y ordenados por canastas: " + mapJugadoresPosicion);
        } else {
            System.out.println("Status code: " + responseJugadoresPosicionMedia.code() +
                    "Mensaje error: " + responseJugadoresPosicionMedia.errorBody());
        }

        /*callJugadoresPosicionMedia = jugadorService.getError();
        responseJugadoresPosicionMedia = callJugadoresPosicionMedia.execute();

        if(!response.isSuccessful()) {
            System.out.println("Status code: " + responseJugadoresPosicionMedia.code() +
                    " Mensaje de error: " + responseJugadoresPosicionMedia.raw() );
        }*/

    }
}
