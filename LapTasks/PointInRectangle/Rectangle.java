package PointInRectangle_02;

public class Rectangle {
    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }
    public boolean contains(Point point) {
        boolean isInVertical =this.getBottomLeft().getY() <= point.getY() &&
                this.getTopRight().getY() >= point.getY();
        boolean isInHorizontal = this.getBottomLeft().getX() <= point.getX() &&
                this.getTopRight().getX() >= point.getX();

       return isInHorizontal && isInVertical;
    }
}
