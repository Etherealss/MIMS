package common.factory;

import controller.ConsumptionController;
import controller.MusicController;
import controller.UserController;

/**
 * @author wtk
 * @description
 * @date 2021-05-24
 */
public class ControllerFactory {

    public static UserController getUserController() {
        return new UserController();
    }

    public static ConsumptionController getConsumptionController() {
        return new ConsumptionController();
    }


    public static MusicController getMusicController() {
        return new MusicController();
    }

}
