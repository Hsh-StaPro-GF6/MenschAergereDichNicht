public class AiMember extends GameMember {
    private int behaviour;
    private int speedBehaviour;
    private boolean kevin;

    public AiMember(String name, int behaviour, int speedBehaviour, boolean kevin) {
        super(name);

        this.behaviour = behaviour;
        this.speedBehaviour = speedBehaviour;
        this.kevin = kevin;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public int getSpeedBehaviour() {
        return speedBehaviour;
    }

    public boolean isKevin() {
        return kevin;
    }
}
