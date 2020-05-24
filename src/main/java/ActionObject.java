public class ActionObject {

    private Integer id;
    private Actions action;

    public ActionObject(Integer id, Actions action) {
        this.id = id;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public Actions getAction() {
        return action;
    }
}
