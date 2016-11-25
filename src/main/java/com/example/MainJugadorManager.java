package com.example;

import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Navy on 17/11/2016.
 */
public class MainJugadorManager implements JugadorCallback {

    private JugadorManager jugadorManager = new JugadorManager();
    private Jugador nuevoJugador;
    private boolean actualizado = false;

    public static void main(String[] args){

        MainJugadorManager mainJugadorManager = new MainJugadorManager();
        mainJugadorManager.initDemo();


    }

    private void initDemo() {

        //Creación de un nuevo Jugador
        Jugador jugador = new Jugador();
        jugador.setNombre("retrofit");
        jugador.setCanastas(15);
        jugador.setAsistencias(12);
        jugador.setRebotes(2);
        jugador.setPosicion(Posicion.Alero);


        //Invocamos al metodo crearJugador del JugadorManager (encapsula la funcionalidad ofrecida por retrofit)
        //La referencia this es la instancia de la clase MainJugadorManager que implementa JugadorCallback
        jugadorManager.crearJugador(jugador, this);
        //jugadorManager.deleteJugador(jugador.getId(), this);

        jugadorManager.getAllJugadores(this);

    }




    @Override
    public void onSuccess(List<Jugador> jugadorList) {
        //En Android se mostraría la información de la lista de Jugadores por pantalla
        //Muestro todos los jugadores
        System.out.println("Muestro todos los jugadores : ");
        System.out.println();
        System.out.println(jugadorList);
        System.out.println();
    }

    @Override
    public void onSuccess(Jugador jugador) {
        //En Android se mostraría la información del Jugador por pantalla
        System.out.println("Muestro el jugador creado o actualizado :");
        System.out.println();
        System.out.println(jugador);
        System.out.println();
        nuevoJugador = jugador;

        if(! actualizado) {
            actualizado = true;
            //Estas instrucciones se tienen que poner en el callback porque las llamadas al servidor son asincronas
            nuevoJugador.setCanastas(20);
            jugadorManager.updateJugador(nuevoJugador, this);
        }

    }

    @Override
    public void onFailure(Throwable t) {
        //En Android se mostraría el error en la Interfaz de Usuario
        //Aquí solo mostramos el error por la consola
        System.out.println(t);
    }
}
