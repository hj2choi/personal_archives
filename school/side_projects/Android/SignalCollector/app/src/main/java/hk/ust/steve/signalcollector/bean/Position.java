package hk.ust.steve.signalcollector.bean;

import hk.ust.steve.signalcollector.Consts;

public class Position {

    private double y;
    private double x;
    private double oritationOffset;
    private String areaId = "";
    public String resultType = "";

    public static final String wifiResultStr = "wifi";
    public static final String magResultStr = "mag";

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y, String areaId) {
        this.x = x;
        this.y = y;
        this.areaId = areaId;
    }

    public Position() {}

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getOritationOffset() {
        return oritationOffset;
    }

    public void setOritationOffset(double oritationOffset) {
        this.oritationOffset = oritationOffset;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp = -1;

    @Override
    public String toString() {
        return "area" + areaId + ": (" + x + ", " + y + ")";
    }
    
    public boolean equals(Position other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(x - other.x) < Consts.eps && Math.abs(y - other.y) < Consts.eps);
    }

    public Position add(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public Position multiple(double multipler) {
        return new Position(x * multipler, y * multipler);
    }

    public double dist(Position other) {
        double xDiff = x - other.x;
        double yDiff = y - other.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public double getNorm() {
        return Math.sqrt(x * x + y * y);
    }

    public Position getMiddlePosition(Position other) {
        double middleX = (x + other.x) / 2.0;
        double middleY = (y + other.y) / 2.0;
        return new Position(middleX, middleY);
    }

    public double getSlope(Position other) throws Exception {
        if (Math.abs(x - other.x) < Consts.eps) {
            throw new Exception("unavailable slope between " + this + " and " + other);
        }
        return (y - other.y) / (x - other.x);
    }

    public double dist2Line(Position begin, Position end) {
        double cross = (end.x - begin.x) * (x - begin.x) + (end.y - begin.y) * (y - begin.y);
        if (cross <= 0) {
            return dist(begin);
        }

        double lineLenSquare = Math.pow(begin.dist(end), 2);
        if (cross >= lineLenSquare) {
            return dist(end);
        }

        double rate = cross / lineLenSquare;
        double px = begin.x + (end.x - begin.x) * rate;
        double py = begin.y + (end.y - begin.y) * rate;
        return dist(new Position(px, py));
    }

    public Position clone(){
        return new Position(x, y);
    }
}
