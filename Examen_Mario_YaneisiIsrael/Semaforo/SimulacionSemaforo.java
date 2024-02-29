package Semaforo;

public class SimulacionSemaforo {
	
	    private static final Object lock = new Object();
	    private static String estadoActual = "ROJO";

	    public static void main(String[] args) {
	        Thread hiloRojo = new Thread(() -> {
	            while (true) {
	                synchronized (lock) {
	                    if ("ROJO".equals(estadoActual)) {
	                        System.out.println("Rojo - Pare por 5 segundos");
	                        try {
	                            Thread.sleep(5000); 
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                        estadoActual = "VERDE";
	                        lock.notifyAll();
	                    }
	                }
	            }
	        });

	        Thread hiloVerde = new Thread(() -> {
	            while (true) {
	                synchronized (lock) {
	                    while (!"VERDE".equals(estadoActual)) {
	                        try {
	                            lock.wait();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    System.out.println("Verde - Avance por 4 segundos");
	                    try {
	                        Thread.sleep(4000); 
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    estadoActual = "AMARILLO";
	                    lock.notifyAll();
	                }
	            }
	        });

	        Thread hiloAmarillo = new Thread(() -> {
	            while (true) {
	                synchronized (lock) {
	                    while (!"AMARILLO".equals(estadoActual)) {
	                        try {
	                            lock.wait();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    System.out.println("Amarillo - Precaución por 2 segundos");
	                    try {
	                        Thread.sleep(2000); 
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    estadoActual = "ROJO";
	                    lock.notifyAll();
	                }
	            }
	        });

	        hiloRojo.start();
	        hiloVerde.start();
	        hiloAmarillo.start();
	    }
	}


