package com.example;

import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Navy on 17/11/2016.
 */
public class MainJugadorManager {

    private JugadorManager jugadorManager = new JugadorManager();
    private Jugador nuevoJugador;

    public static void main(String[] args) throws InterruptedException{

        MainJugadorManager mainJugadorManager = new MainJugadorManager();
        mainJugadorManager.initDemo();


    }

    //Hacemos un CRUD basico de un jugador.
    //Al ser un main asincrono, hara las peticiones ordenadas, pero las respuestas pueden llegar en cualquier orden.
    //Hacemos el thread sleep, para evitar que se puede intentar borrar un jugador, cuando es posible que todavia no se haya creado.
    //En cada peticion, pasamos un callback con sus metodos, para recibir las notificiones asincronas.

    private void initDemo() throws InterruptedException {

        jugadorManager.getAllJugadores(new JugadorCallback() {
            @Override
            public void onSuccess() {

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

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        //Creación de un nuevo Jugador
        Jugador jugador = new Jugador();
        jugador.setNombre("retrofit");
        jugador.setCanastas(15);
        jugador.setAsistencias(12);
        jugador.setRebotes(2);
        jugador.setPosicion(Posicion.Alero);


        //Invocamos al metodo crearJugador del JugadorManager (encapsula la funcionalidad ofrecida por retrofit)
        //La referencia this es la instancia de la clase MainJugadorManager que implementa JugadorCallback
        jugadorManager.crearJugador(jugador, new JugadorCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onSuccess(List<Jugador> jugadorList) {

            }

            @Override
            public void onSuccess(Jugador jugador) {
                //En Android se mostraría la información del Jugador por pantalla
                System.out.println("Muestro el jugador creado:");
                System.out.println();
                System.out.println(jugador);
                System.out.println();
                nuevoJugador = jugador;

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        Thread.currentThread().sleep(5000L);

        nuevoJugador.setCanastas(20);

        jugadorManager.updateJugador(nuevoJugador, new JugadorCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onSuccess(List<Jugador> jugadorList) {

            }

            @Override
            public void onSuccess(Jugador jugador) {
                System.out.println("Muestro el jugador actualizado:");
                System.out.println();
                System.out.println(jugador);
                System.out.println();
                nuevoJugador = jugador;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        Thread.currentThread().sleep(5000L);

        jugadorManager.deleteJugador(nuevoJugador.getId(), new JugadorCallback() {
            @Override
            public void onSuccess() {
                System.out.println("Jugador borrado correctamente");
            }

            @Override
            public void onSuccess(List<Jugador> jugadorList) {

            }

            @Override
            public void onSuccess(Jugador jugador) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
