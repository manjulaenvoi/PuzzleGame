package com.tele;

class Box {

    private boolean user, price, host;
    private int count;

    public Box(boolean user, boolean price, boolean host, int count) {
        this.user = user;
        this.price = price;
        this.host = host;
        this.count = count;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Box{" +
                "user=" + user +
                ", price=" + price +
                ", host=" + host +
                ", count=" + count +
                '}';
    }
}