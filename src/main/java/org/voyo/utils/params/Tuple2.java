package org.voyo.utils.params;


public class Tuple2<T,S> {
    private T t1;
    private S t2;

    public Tuple2(T t1,S t2){
        this.t1=t1;
        this.t2=t2;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }
    public void setT2(S t2) {
        this.t2 = t2;
    }
    public T getT1() {
        return t1;
    }
    public S getT2() {
        return t2;
    }
}
