package sdx.ml.phedex.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdx.ml.phedex.pojos.PhedexCircuit;

public class PhedexCircuitsHolder {

    public final ConcurrentMap<String, PhedexCircuit> circuitHolder;

    public PhedexCircuitsHolder() {
        circuitHolder = new ConcurrentHashMap<>();
    }

    public PhedexCircuit saveCircuit(PhedexCircuit circuit) {
        return circuitHolder.putIfAbsent(circuit.getId(), circuit);
    }

    public PhedexCircuit deleteCircuit(String id) {
        return circuitHolder.remove(id);
    }

    public PhedexCircuit getCircuit(String id) {
        return circuitHolder.get(id);
    }

    public Set<PhedexCircuit> getAllCircuits() {
        return new HashSet<PhedexCircuit>(circuitHolder.values());
    }
}
