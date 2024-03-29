package robotService.entities.services;

public class MainService extends BaseService{
    private final static int CAPACITY = 30;
    public MainService(String name) {
        super(name, CAPACITY);
    }
}
