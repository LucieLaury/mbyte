package fr.jayblanc.mbyte.manager.topology;

public interface TopologyService {

    String lookup(String name);

    boolean isRegistered();

    void checkin();
}
