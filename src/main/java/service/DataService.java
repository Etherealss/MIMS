package service;

import common.exception.BadRequestException;

import java.io.IOException;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public interface DataService {

    void saveData(String dataType) throws IOException, BadRequestException;

    void exportData(String dataType, String filepath) throws IOException, BadRequestException;
}
