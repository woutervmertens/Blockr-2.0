public enum Direction {
    UP {
        @Override
        public Direction NeighbourLeft(){
            return values()[values().length - 1];
        }
    },
    RIGHT,
    DOWN,
    LEFT{
        @Override
        public Direction NeighbourRight(){
            return values()[0];
        }
    };

    public Direction NeighbourLeft(){
        return values()[ordinal() - 1];
    }
    public Direction NeighbourRight(){
        return values()[ordinal() + 1];
    }
}
