package org.testing.alg;

import java.util.NoSuchElementException;

public class ClosedHashingProb<E>  {
    private static final int CAPACITY = 30;
    long size;
    int firstDelPos = -1;
    private final Object[] data;
    Probe prob = Probe.LINEAR;


    private static class Deleted<E> {
        E val;

        Deleted(final E val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Deleted{" +
                    "val=" + val.toString() +
                    '}';
        }
    }


    public enum Probe {
        LINEAR,
        QUAD,
        DOUBLE
    }

    public ClosedHashingProb(final int capacity,final Probe prob) {
        data = new Object[primeUp(capacity)];
        this.prob = prob;
    }

    public ClosedHashingProb(final Probe prob) {
        data = new Object[CAPACITY];
        this.prob = prob;
    }

    public ClosedHashingProb(final int capacity) {
        data = new Object[primeUp(capacity)];
    }

    public ClosedHashingProb() {
        data = new Object[CAPACITY];
    }


    private static int primeUp(final int n) {
        int prime = n;
        if (prime % 2 == 0) {
            prime += 1;
        }
        boolean foundPrime;
        do {
            foundPrime = true;
            for (int i = 3; i <= Math.sqrt(prime); i += 2) {
                if (prime % i == 0) {
                    foundPrime = false;
                    prime += 2;
                    break;
                }
            }
        }
        while (!foundPrime);
        return prime;
    }


    private int hash2(final Object obj) {
        final int max = data.length - 2;
        return max - Math.abs(obj.hashCode() % max);
    }

    private int hash(final Object obj) {
        return Math.abs(obj.hashCode() % data.length);
    }

    @SuppressWarnings("unchecked")
    private int linearProbe(final Object obj) {
        int pos = hash(obj);
        while(true) {
            if (data[pos] == null) {
                if (firstDelPos == -1) {
                    return pos;
                }
                else {
                    return firstDelPos;
                }
            } else if (data[pos].equals(obj)) {
                return -(pos+1);
            }
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (firstDelPos == -1) {
                    firstDelPos = pos;
                }
                pos = (pos + 1) % data.length;
            }
            else {
                pos = (pos + 1) % data.length;
            }
        }
    }


    @SuppressWarnings("unchecked")
    private int quadraticProbe(final Object obj) {
        int pos = hash(obj);
        int i = 1;
        int delPos = -1;
        while (true) {
            if (data[pos] == null) {
                if (delPos == -1) {
                    return pos;
                }
                else {
                    return delPos;
                }
            }
            else if (data[pos].equals(obj)) {
                return -(pos+1);
            }
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (delPos == -1) {
                    delPos = pos;
                }
                pos = (pos+i) % data.length;
                i += 2;
            }
            else {
                pos = (pos+i) % data.length;
                i += 2;
            }
        }
    }


    @SuppressWarnings("unchecked")
    private int doubleProbe(final Object obj) {
        int pos = hash(obj);
        final int amt = hash2(obj);
        while(true) {
            if (data[pos] == null) {
                if (firstDelPos == -1) {
                    return pos;
                }
                else {
                    return firstDelPos;
                }
            }
            else if (data[pos].equals(obj)){
                return -(pos+1);
            }
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (firstDelPos == -1) {
                    firstDelPos = pos;
                }
                pos = (pos + amt) % data.length;
            }
            else {
                pos = (pos+amt) % data.length;
            }
        }
    }

    private int probe(final Object obj) {
        return switch (prob) {
            case QUAD -> quadraticProbe(obj);
            case LINEAR -> linearProbe(obj);
            case DOUBLE -> doubleProbe(obj);
            default -> throw new IllegalStateException("probe");
        };
    }

    private boolean isFull() {
        int cntEl = 0;
        for (final Object datum : data) {
            if (datum != null) {
                if (datum instanceof Deleted){
                    continue;
                }
                cntEl++;
            }
        }
        return cntEl == data.length;
    }


    public boolean insert(final E el) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("all array is full");
        }
        final int pos = probe(el);
        if (pos < 0) {
            return false;
        }
        else {
            ++size;
            data[pos] = el;
            return true;
        }
    }

    public boolean contains(final Object obj) {
        return probe(obj) < 0;
    }

    public int find(final Object obj) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                if (data[i] instanceof Deleted) {
                    if (data[i].equals(obj)) {
                        break;
                    }
                } else {
                    if (data[i].equals(obj)){
                        return i;
                    }
                }
            }
        }
        throw new NoSuchElementException("no such el :c");
    }

    public boolean remove(final Object obj) {
        final int pos = probe(obj);
        if (pos >= 0) {
            return false;
        }
        else {
            --size;
            final int delPos = -(pos+1);
            data[delPos] = new Deleted<>(data[delPos]);
            return true;
        }
    }

//    public void printTable() {
//        for (int i = 0; i < data.length; i++) {
//            if (data[i] != null) {
//                if (data[i] instanceof Deleted) {
//                    System.out.println(i + ":" + "[" + data[i] + "]");
//                    continue;
//                }
//                else {
//                    System.out.println(i + ":" + data[i]);
//                }
//                int h = hash(data[i]);
//                if (h == i) System.out.println(" (*)");
//                else {
//                    System.out.println(" (hash=" + h + ")");
//                    if (prob.equals(Probe.DOUBLE)) System.out.println(" (hash2=" + hash2(data[i]) + ")");
//                }
//            }
//        }
//    }

    public void setProb(final Probe prob) {
        this.prob = prob;
    }
}
