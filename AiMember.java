public class AiMember extends GameMember {
    private int behaviour;
    private int speedBehaviour;

    public AiMember(String name, int behaviour, int speedBehaviour){
        super(name);

        this.behaviour = behaviour;
        this.speedBehaviour = speedBehaviour;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public int getSpeedBehaviour() {
        return speedBehaviour;
    }
}
