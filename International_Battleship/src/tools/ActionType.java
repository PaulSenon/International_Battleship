package tools;

public enum ActionType {
    MOVE,
    SELECT,
    SPECIAL,
    SHOOT;

    public static ActionType DEFAULT() {
        return ActionType.MOVE;
    }
    public static ActionType INIT() {
        return ActionType.SELECT;
    }
}