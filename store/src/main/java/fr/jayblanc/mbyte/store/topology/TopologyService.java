package fr.jayblanc.mbyte.store.topology;

import fr.jayblanc.mbyte.store.topology.entity.Neighbour;

import java.util.List;

public interface TopologyService {

    List<Neighbour> list() throws TopologyException;

    boolean isRegistered();

    void checkin();

}
