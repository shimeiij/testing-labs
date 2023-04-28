package org.testing.alg;

import java.util.Set;

public class ClosedHashingProb<E>  {
    private static final float LF = 0.75F;
    private static final int CAPACITY = 101;
    private int size = 0;
    private Object[] data;
    Probe prob;


    private static class Deleted<E> {
        E val;

        Deleted(E val) {
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

    public ClosedHashingProb(int capacity, Probe prob) {
        data = new Object[primeUp(capacity)];
        this.prob = prob;
    }

    public ClosedHashingProb(Probe prob) {
        data = new Object[CAPACITY];
        this.prob = prob;
    }


    private static int primeUp(int n) {
        int prime = n;
        if (prime % 2 == 0) {
            prime += 1;
        }
        boolean found_prime;
        do {
            found_prime = true;
            for (int i = 3; i <= Math.sqrt(prime); i += 2) {
                if (prime % i == 0) {
                    found_prime = false;
                    prime += 2;
                    break;
                }
            }
        }
        while (!found_prime);
        return prime;
    }


    private int hash2(Object obj) {
        int max = data.length - 2;
        return max - Math.abs(obj.hashCode() % max);
    }

    private int hash(Object obj) {
        return Math.abs(obj.hashCode() % data.length);
    }

    @SuppressWarnings("unchecked")
    private int linearProbe(Object obj) {
        int pos = hash(obj);
        int first_del_pos = -1;
        while(true) {
            if (data[pos] == null) {
                if (first_del_pos == -1) {
                    return pos;
                }
                else {
                    return first_del_pos;
                }
            } else if (data[pos].equals(obj)) {
                return -(pos+1);
            }
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (first_del_pos == -1) {
                    first_del_pos = pos;
                }
                pos = (pos + 1) % data.length;
            }
            else {
                pos = (pos + 1) % data.length;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private int quadraticProbe(Object obj) {
        int pos = hash(obj);
        int i = 1;
        int first_del_pos = -1;
        while (true) {
            if (data[pos] == null) {
                if (first_del_pos == -1) {
                    return pos;
                }
                else {
                    return first_del_pos;
                }
            }
            else if (data[pos].equals(obj)) {
                return -(pos+1);
            }
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (first_del_pos == -1) {
                    first_del_pos = pos;
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
    private int doubleProbe(Object obj) {
        int pos = hash(obj);
        int amt = hash2(obj);
        int first_del_pos = -1;
        while(true) {
            if (data[pos] == null) {
                if (first_del_pos == -1) {
                    return pos;
                }
                else {
                    return first_del_pos;
                }
            }
            else if (data[pos].equals(obj)) return -(pos+1);
            else if (data[pos] instanceof Deleted) {
                if (((Deleted<E>) data[pos]).val.equals(obj)) {
                    return pos;
                }
                else if (first_del_pos == -1) {
                    first_del_pos = pos;
                }
                pos = (pos + amt) % data.length;
            }
            else {
                pos = (pos+amt) % data.length;
            }
        }
    }

    private int probe(Object obj) {
        return switch (prob) {
            case QUAD -> quadraticProbe(obj);
            case LINEAR -> linearProbe(obj);
            case DOUBLE -> doubleProbe(obj);
            default -> throw new IllegalStateException("probe");
        };
    }


    public boolean insert(E el) {
        int pos = probe(el);
        if (pos < 0) {
            return false;
        }
        else {
            ++size;
            data[pos] = el;
            return true;
        }
    }

    public boolean contains(Object obj) {
        return probe(obj) < 0;
    }

    public int find(Object obj) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                if (data[i] instanceof Deleted) {
                    if (data[i].equals(obj)) {
                        System.out.println("deleted");
                        return -1;
                    }
                } else {
                    if (data[i].equals(obj)) return i;
                }
            }
        }
        return -1;
    }

    public boolean remove(Object obj) {
        int pos = probe(obj);
        if (pos >= 0) {
            return false;
        }
        else {
            --size;
            int del_pos = -(pos+1);
            data[del_pos] = new Deleted<>(data[del_pos]);
            return true;
        }
    }

    public void printTable() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                if (data[i] instanceof Deleted) {
                    System.out.println(i + ":" + "[" + data[i] + "]");
                    continue;
                }
                else {
                    System.out.println(i + ":" + data[i]);
                }
                int h = hash(data[i]);
                if (h == i) System.out.println(" (*)");
                else {
                    System.out.println(" (hash=" + h + ")");
                    if (prob.equals(Probe.DOUBLE)) System.out.println(" (hash2=" + hash2(data[i]) + ")");
                }
            }
        }
    }
}
