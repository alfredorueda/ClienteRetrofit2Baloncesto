package com.example;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Navy on 03/11/2016.
 */
public interface JugadorService {

    @GET("/jugadores")
    Call<List<Jugador>> getAllJugadores();

    @GET("/jugadores/{id}")
    Call<Jugador> getJugador(@Path("id") Long id);

    @GET("/jugadoresError")
    Call<List<Jugador>> getError();

    @POST("/jugadores")
    Call<Jugador> createJugador(@Body Jugador jugador);

    @PUT("/jugadores")
    Call<Jugador> updateJugador(@Body Jugador jugador);

    @DELETE("/jugadores/{id}")
    Call<Void> deleteJugador(@Path("id") Long id);

    @GET("/jugadores/{canastas}")
    Call<List<Jugador>> findAllJugadoresCanastas();

    @GET("/jugadores/byCanastas/{canastas}")
    Call<List<Jugador>> findByCanastasGreaterThanEqual(@Path("canastas") int canastas);

    @GET("/jugadores/ByCanastas/{min}/{max}")
    Call<List<Jugador>> findByCanastasBetween(@Path("min") int min, @Path("max") int max);

    @GET("/jugadores/posicionAndMedia")
    Call<Map<Posicion, Collection<Jugador>>> findByPosicionAndMedia();

    @GET("/jugadores/byPosicionAllJugadores")
    Call<Map<Posicion, Collection<Jugador>>> findByAllPosiciones();


}
