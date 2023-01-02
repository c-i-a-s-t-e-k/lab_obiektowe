package agh.ics.darvin;

public class World {
    public static void main(String[] args){
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation);
        System.out.println("Symulacja rozoczęta");
        simulationThread.start();
        try {Thread.sleep(1000);}
        catch (InterruptedException e){
            System.out.println("world interrupted " + e.getMessage());
        }
    }
}
